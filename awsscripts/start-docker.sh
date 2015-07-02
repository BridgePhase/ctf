#!/bin/bash
echo "Building image now"
sudo docker build -t ctf-tomcat /home/ubuntu/docker
echo "Image built"
# We run this in detached mode so that it doesn't hang code deploy
sudo docker run -d --name ctf-deployment -ti -p 8080:8080 ctf-tomcat