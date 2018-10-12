FROM hub.mooc.com:10000/micro-service/openjdk:8-jre
MAINTAINER songjiang whusj@163.com

COPY target/user-edge-service-1.0-SNAPSHOT.jar /user-edge-service.jar

ENTRYPOINT [ "java", "-jar", "/user-edge-service.jar" ]