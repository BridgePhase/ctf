#!/bin/bash
./gradlew build awsRevision
./gradlew bootRun &
protractor e2e/configuration.js