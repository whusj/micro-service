#/bin/bash
echo 'clean images start...'
docker rmi api-gateway-zuul:latest
docker rmi course-edge-service:latest
docker rmi course-service:latest
docker rmi user-edge-service:latest
docker rmi user-service:latest
docker rmi message-service:latest
echo 'clean images finished.'
echo 'clean package install start...'
cd /project/micro-service/
mvn clean package install
echo 'clean package install finished.'
echo 'build images start...'
cd /project/micro-service/message-thrift-python-service/
sh build.sh
cd /project/micro-service/user-thrift-service
sh build.sh
cd /project/micro-service/user-edge-service
sh build.sh
cd /project/micro-service/course-dubbo-service
sh build.sh
cd /project/micro-service/course-edge-service
sh build.sh
cd /project/micro-service/api-gateway-zuul
sh build.sh
echo 'build images finished.'