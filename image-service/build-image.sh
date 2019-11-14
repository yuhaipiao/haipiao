#!/bin/sh

../gradlew build
docker build -t haipiaodev/image-service:0.0.1 .
