#!/bin/bash
if [ "${TRAVIS_PULL_REQUEST}" = "false" ] && [ "${TRAVIS_BRANCH}" = "docker" ]; then
	echo "Running docker revision"
	./gradlew clean build dockerRevision
else
	echo "Running regular revision"
	./gradlew clean build awsRevision
fi
echo "Starting server now" 
export production=true
java -jar build/libs/ctf-1.0.jar & >/dev/null 2>&1
echo "Waiting for server now"
sleep 15
echo "Running protractor tests now"
protractor e2e/configuration.js