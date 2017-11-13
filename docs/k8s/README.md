# Kubernetes on Google Container Engine (GKE)
## Reproducing current setup
### Step by step
### Explanation
#### Instance types

First and foremost, to avoid all the Pod scheduling issues I had and my cluster dying, avoiding Google's `f1-micro` instance type like the plague worked wonders.  

These machines only have 0.6GB of RAM each, and can only use 0.2 vCPU (these vCPUs are shared amongst other customers). Whenever I managed to get a deployment working, the app was only healthy for about 1 minute before either an Out Of Memory exception occurred, or none of the `f1-micro` nodes were ready due to lack of available CPU cycles.  

When initially creating the cluster through the Google Cloud Platform, I had to specify a minimum of 3 nodes.

Adding more nodes didn't help at all, so Autoscaling didn't work either.  

The answer to these problems seemed to be switching over to the `g1-small` instance type for the node pool. As soon as I switched, I noticed I only needed 1 VM instance running to run everything, and all my Pod scheduling nightmares ended - I didn't have any minimum number of nodes constraint like `f1-micro` does.

I am not using Autoscaling since I want to demonstrate to David the flexibility of the cluster (its literally one short command to resize the number of cluster nodes).

