FROM hub.mooc.com:10000/micro-service/openjdk:8-jre
MAINTAINER songjiang whusj@163.com

COPY target/course-edge-service-1.0-SNAPSHOT.jar /course-edge-service.jar

ENTRYPOINT ["java", "-jar", "/course-edge-service.jar"]
