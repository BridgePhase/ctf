#!/bin/bash
./gradlew build awsRevision
webdriver-manager start &
./gradlew bootRun &
protractor e2e/configuration.js