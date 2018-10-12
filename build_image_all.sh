#/bin/bash
echo 'clean images start...'
docker rmi -f api-gateway-zuul:latest
docker rmi -f course-edge-service:latest
docker rmi -f course-service:latest
docker rmi -f user-edge-service:latest
docker rmi -f user-service:latest
docker rmi -f message-service:latest

docker rmi -f hub.mooc.com:10000/micro-service/api-gateway-zuul:latest
docker rmi -f hub.mooc.com:10000/micro-service/course-edge-service:latest
docker rmi -f hub.mooc.com:10000/micro-service/course-service:latest
docker rmi -f hub.mooc.com:10000/micro-service/user-edge-service:latest
docker rmi -f hub.mooc.com:10000/micro-service/user-service:latest
docker rmi -f hub.mooc.com:10000/micro-service/message-service:latest
echo 'clean images finished.'
echo 'clean package install start...'
#cd /project/micro-service/
#mvn clean package install
echo 'clean package install finished.'
echo 'build images start...'
cd /project/micro-service/message-thrift-python-service/
mvn clean package install
sh build.sh
cd /project/micro-service/user-thrift-service
mvn clean package install
sh build.sh
cd /project/micro-service/user-edge-service
mvn clean package install
sh build.sh
cd /project/micro-service/course-dubbo-service
mvn clean package install
sh build.sh
cd /project/micro-service/course-edge-service
mvn clean package install
sh build.sh
cd /project/micro-service/api-gateway-zuul
mvn clean package install
sh build.sh
echo 'build images finished.'