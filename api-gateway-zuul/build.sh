#!/usr/bin/env bash
mvn package
docker build -t api-gateway-zuul:latest .

#docker run -idt -p api-gateway-zuul:latest
