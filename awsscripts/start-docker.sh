#!/bin/bash
echo "Building image now"
sudo docker build -t ctf-tomcat .
echo "Image built"
#sudo docker run -d --name ctf-deployment -ti -p 8080:8080 ctf-tomcat