# Kubernetes on Google Container Engine (GKE)
It is extremely helpful to get a grasp on Pods + Deployments concepts in Kubernetes, useful docs [here](https://kubernetes.io/docs/concepts/)
## Reproducing current setup
### Step by step
**Prerequisites:**   
1. Generate LetsEncrypt certificates for domain (I temporarily pointed mald.domhoney.com at my Pi and configured LetsEncrypt from there) and upload `fullchain.pem` + `privkey.pem` to Cloud Shell.      
It is possible to reserve a static IP address in GKE, but the steps below automatically generate a static address.   
2. Whenever `kubectl` is specified, Cloud Shell should be used. It is assumed gcloud has already configured the connection to the cluster (command to do this is `gcloud container clusters get-credentials <your-cluster-name> --zone <your-region-and-zone>`)
3. Create a Gitlab personal token [link](https://gitlab.com/profile/personal_access_tokens) with `read_registry` access only. Since we are using Gitlab's private container registry, Kubernetes will need to be able to read from it. Store the token on your workstation (to be copied into Cloud Shell later).

**Steps**
1. Create a cluster on Google Container Engine, region: `europe-west2` (London), size: 1, instance type: `g1-small`, node version: `1.8.2-gke.0`
2. Create a Kubernetes [TLS Secret](https://kubernetes.io/docs/concepts/services-networking/ingress/#tls) using [mald-tls-secret.yaml](docs/k8s/mald-tls-secret.yaml) as a template. 
3. Create a Kubernetes docker-registry Secret: `kubectl create secret docker-registry registry.gitlab.com --docker-server=registry.gitlab.com --docker-username=gitlab-ci-token --docker-password=<gitlab personal access token> --docker-email=<your gitlab email>` so Kubernetes can pull from the Gitlab project's private container registry.
4. Create a Kubernetes [Service](https://kubernetes.io/docs/concepts/services-networking/service/) (`kubectl create -f <your-yaml-file>`) based on [mald-service.yaml](docs/k8s/mald-service.yaml). `spec.selector` specifies the Pods to include in the Service.
5. Create a Kubernetes [Ingress](https://kubernetes.io/docs/concepts/services-networking/ingress/) (`kubectl create -f <your-yaml-file>`) based on [mald-ingress.yaml](docs/k8s/mald-ingress.yaml). `spec.backend` specifies the previously created Service as the default service to forward requests to. Creating this Ingress will create 1 GCE forwarding rule (HTTPS only) which costs $22.50 a month. A static IP address will also be assigned automatically.   
Notice how `spec.tls.secretName` matches step 2's secret name `mald-tls-secret`
6. Write a Kubernetes [Deployment](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/) spec to be used in Gitlab CI deployment job. Should be fine copying the `k8s-deploy` job defined in [.gitlab-ci.yml](.gitlab-ci.yml), [k8s-deploy.sh](k8s-deploy.sh), and slightly adjusting [k8s-deployment-template.yml](k8s-deployment-template.yml).   
In the `k8s-deployment-template.yml` file, notice how `spec.template.metadata.labels` matches step 4's Service `spec.selector`.   
`spec.template.spec.containers.ports.containerPort` matches step 4's Service `spec.ports.targetPort`.   
`spec.template.spec.imagePullSecrets` matches step 3's secret name `registry.gitlab.com`.   


### Explanation
#### Instance types

To avoid all the Pod scheduling issues I had with my cluster dying, avoiding Google's `f1-micro` instance type like the plague worked wonders.  

These machines only have 0.6GB of RAM each, and can only use 0.2 vCPU (these vCPUs are shared amongst other customers). Whenever I managed to get a deployment working, the app was only healthy for about 1 minute before either an Out Of Memory exception occurred in all Pod replicas, or none of the `f1-micro` nodes were ready due to lack of available CPU cycles.  

When initially creating the cluster through the Google Cloud Platform, I had to specify a minimum of 3 nodes.

Adding more nodes didn't help at all, so Autoscaling didn't work either.  

The answer to these problems seemed to be switching over to the `g1-small` instance type for the node pool. As soon as I switched, I noticed I only needed 1 VM instance running to run everything, and all my Pod scheduling nightmares ended - I didn't have any minimum number of nodes constraint like `f1-micro` does.

Using 1x `g1-small` over 3x `f1-micro` is also slightly cheaper (even with 1 `f1-micro` being free), eating through $300 won't take as long. 

I am not using Autoscaling since I want to demonstrate to David the flexibility of the cluster; its one short command to resize the number of cluster nodes, and another.

#### k8s-deploy.sh

A simpler way to write a Gitlab CI job deploy job would be NOT templating `k8s-deployment-template.yml` and just use the image's `:latest` tag (see commit ecc528ed - file `k8s-deployment-template.yml`).   

I did try this for a while, but it's not good practise for dealing with dodgy Deployments (e.g. app crashing on start) - if the `k8s-deploy` CI job is ran again on an earlier commit, Kubernetes will still pull the `:latest` image and still perform a dodgy Deployment. The same thing happens using `kubectl rollout undo deployment ...`.   

I wanted to be able to specify a particular image (app revision) in the Deployment yaml using the commit SHA - the `docker-push` job already pushes an image tagged with `$CI_COMMIT_SHA`, and the only way to refer to that easily in the Deployment spec is by treating the `k8s-deployment-template.yml` as a bash template so the `$CI_COMMIT_SHA` env var can be properly expanded. Hence `k8s-deploy.sh` and its `render_template()` function.