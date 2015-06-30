#!/bin/bash
./gradlew build awsRevision
echo "Starting server now" 
java -jar build/libs/ctf-1.0.jar
sleep 5
echo "Running protractor tests now"
protractor e2e/configuration.js