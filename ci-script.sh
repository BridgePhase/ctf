#!/bin/bash
./gradlew clean build awsRevision dockerRevision
echo "Starting server now" 
export production=true
export cron_notifications="0 5,17 * * * *"
export cron_cache_clear="0 5,17 * * * *"
java -jar build/libs/ctf-1.0.jar & >/dev/null 2>&1
echo "Waiting for server now"
sleep 15
echo "Running protractor tests now"
protractor e2e/configuration.js