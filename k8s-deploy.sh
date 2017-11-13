#!/usr/bin/env bash

render_template() {
  eval "echo \"$(cat $1)\""
}

render_template k8s-deployment-template.yml > k8s-deployment.yml

echo "$GCLOUD_KEY" > key.json
gcloud auth activate-service-account --key-file key.json
gcloud config set container/use_client_certificate True
gcloud container clusters get-credentials website-cluster --project distributed-mald --zone europe-west2-a

kubectl apply -f k8s-deployment.yml