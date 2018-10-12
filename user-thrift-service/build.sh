#!/usr/bin/env bash

mvn package
docker build -t hub.mooc.com:10000/micro-service/user-service:latest .
docker push hub.mooc.com:10000/micro-service/user-service:latest

#docker run -idt -p 7911:7911 imooc-user-service:latest --mysql.address=192.168.202.1
