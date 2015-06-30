#!/bin/bash
./gradlew build awsRevision 
java -jar build/libs/ctf-1.0.jar
echo "Running protractor tests now"
protractor e2e/configuration.js