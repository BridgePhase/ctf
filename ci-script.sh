#!/bin/bash
./gradlew build awsRevision
echo "Starting server now" 
./gradlew bootRun &
echo "Waiting for server now"
sleep 30
echo "Running protractor tests now"
protractor e2e/configuration.js