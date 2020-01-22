FROM openjdk:11

COPY build/libs/article-service.jar /
COPY log4j2.xml /
# no need to copy properties file, since it will be mounted by k8s.
#COPY application.properties /
WORKDIR /
CMD ["java","-jar","article-service.jar"]
