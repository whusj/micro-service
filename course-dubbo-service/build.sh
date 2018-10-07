#!/usr/bin/env bash


mvn package
if [ $? != 0 ];then
    echo "mvn package failed!!!"
    exit 1
fi
docker build -t course-service:latest .

#docker run -idt course-service:latest  --mysql.address=192.168.202.61 --zookeeper.address=192.168.202.61:2181?backup=192.168.202.62:2181,192.168.202.63:2181
