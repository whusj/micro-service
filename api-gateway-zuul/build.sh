#!/usr/bin/env bash
mvn package
docker build -t hub.mooc.com:10000/micro-service/api-gateway-zuul:latest .
docker push hub.mooc.com:10000/micro-service/api-gateway-zuul:latest

#docker run -idt -p api-gateway-zuul:latest
