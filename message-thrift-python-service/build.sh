#!/usr/bin/env bash

docker build -t hub.mooc.com:10000/micro-service/message-service:latest .
docker push hub.mooc.com:10000/micro-service/message-service:latest
#docker run -idt -p 9090:9090 message-service:latest
