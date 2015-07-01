#!/bin/bash
sudo docker build -t ctf-tomcat .
sudo docker run --name ctf-deployment -ti -p 8080:8080 ctf-tomcat