FROM openjdk:11

COPY build/libs/registration.jar /
COPY log4j2.xml /
# no need to copy properties file, since it will be mounted by k8s.
#COPY config/application.properties /config/
WORKDIR /
CMD ["java","-jar","registration.jar"]
