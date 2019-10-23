#!/bin/sh

kubectl apply -f app/namespace.yaml
kustomize build . | kubectl apply -f -
kubectl apply -f app/dev/
