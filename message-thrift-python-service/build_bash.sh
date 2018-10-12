#!/usr/bin/env bash

docker build -t hub.mooc.com:10000/micro-service/python-base:latest -f Dockerfile.base .
docker push hub.mooc.com:10000/micro-service/python-base:latest