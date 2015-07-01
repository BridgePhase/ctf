#!/bin/bash
./gradlew clean build awsRevision
#echo "Starting server now" 
#export production=true
#./gradlew bootRun & >/dev/null 2>&1
#echo "Waiting for server now"
#sleep 30
#echo "Running protractor tests now"
#protractor e2e/configuration.js