#!/bin/bash
# In new deployments or failed deployments we don't care if we can't stop docker
# containers because they don't exist, that's why the || true is there
sudo docker stop ctf-deployment || true
sudo docker rm ctf-deployment || true
sudo docker rmi -f ctf-tomcat || true