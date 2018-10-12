#!/usr/bin/env bash


mvn package
docker build -t hub.mooc.com:10000/micro-service/user-edge-service:latest .
docker push hub.mooc.com:10000/micro-service/user-edge-service:latest

#docker run -idt -p 8082:8082 user-edge-service:latest  --redis.address=192.168.202.61 --redis.port=7001
