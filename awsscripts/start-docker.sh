#!/bin/bash
# We run this in detached mode so that it doesn't hang code deploy
sudo docker run -d --name ctf-deployment -ti -p 8080:8080 jramirez/bridgephase:_TAG_