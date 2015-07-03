#!/bin/bash
# We run this in detached mode so that it doesn't hang code deploy
found=0
while[ $found -eq 0 ]; do
	echo "Waiting for image to appear"
	sleep 30
	found=1
	sudo docker run -d --name ctf-deployment -ti -p 8080:8080 jramirez/bridgephase:_TAG_ || found=0
done
echo "Finished deploying now"