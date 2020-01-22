#!/bin/sh

cp ../deployment/config/shared/log4j2.xml .
../gradlew build
docker build -t haipiaodev/registration:0.0.3 .
rm ./log4j2.xml