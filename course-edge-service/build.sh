#!/usr/bin/env bash

mvn package
docker build -t course-edge-service:latest .

#docker run -idt  course-edge-service:latest --zookeeper.address=192.168.202.61:2181?backup=192.168.202.62:2181,192.168.202.63:2181