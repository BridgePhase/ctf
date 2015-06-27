#!/bin/bash
# Start up Tomcat.
export production=true
export h2location=/home/ubuntu/ctf/data
/home/ubuntu/applications/apache-tomcat-7.0.62/bin/catalina.sh start

# Scheduled task for updating notifications and cache clearing
# To be completed at 5AM and 5PM
export cron_notifications="0 5,17 * * * *"
export cron_cache_clear="0 5,17 * * * *"