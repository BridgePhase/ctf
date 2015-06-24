#!/bin/bash
# Start up Tomcat.
export production=true
/home/ubuntu/applications/apache-tomcat-7.0.62/bin/catalina.sh start

# Scheduled task for updating notifications
# To be completed at 5AM and 5PM
export cron_notifications="0 5,17 * * * *"