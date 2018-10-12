FROM hub.mooc.com:10000/micro-service/openjdk:8-jre
MAINTAINER songjiang whusj@163.com

COPY target/course-dubbo-service-1.0-SNAPSHOT.jar /course-dubbo-service.jar

ENTRYPOINT [ "java" ,"-jar", "/course-dubbo-service.jar" ]
