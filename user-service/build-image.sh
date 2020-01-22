#!/bin/sh

cp ../deployment/config/shared/log4j2.xml .
../gradlew build
docker build -t haipiaodev/user-service:0.0.6 .
rm ./log4j2.xml