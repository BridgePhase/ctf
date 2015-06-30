#!/bin/bash
./gradlew build awsRevision
echo "Starting server now" 
./gradlew bootRun &
sleep 5
echo "Running protractor tests now"
protractor e2e/configuration.js