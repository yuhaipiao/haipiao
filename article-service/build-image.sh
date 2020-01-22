#!/bin/sh

cp ../deployment/config/shared/log4j2.xml .
../gradlew build
docker build -t haipiaodev/article-service:0.0.1 .
rm ./log4j2.xml