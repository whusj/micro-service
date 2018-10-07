FROM openjdk:8-jre
MAINTAINER songjiang whusj@163.com

COPY target/api-gateway-zuul-1.0-SNAPSHOT.jar /api-gateway.jar

ENTRYPOINT [ "java", "-jar", "/api-gateway.jar" ]
