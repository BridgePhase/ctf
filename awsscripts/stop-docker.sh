#!/bin/bash
sudo docker stop ctf-deployment || true
sudo docker rm ctf-deployment || true
sudo docker rmi -f ctf-tomcat || true