#!/usr/bin/env bash
kubectl scale deployment website-deployment --replicas=$SIZE
gcloud container clusters resize website-cluster --zone europe-west2-a --size $SIZE