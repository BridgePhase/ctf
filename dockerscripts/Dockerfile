FROM tomcat:7.0.62-jre7
ENV production=true
ENV h2location=/home/local/ctf/data
# Scheduled task for updating notifications and cache clearing
# To be completed at 5AM and 5PM
ENV cron_notifications="0 5,17 * * * *"
ENV cron_cache_clear="0 5,17 * * * *"
# This serves as our re-direction when people browse without the 
# context root of the application. Simple, yet effective
ADD index.jsp /usr/local/tomcat/webapps/ROOT/
ADD ctf.war /usr/local/tomcat/webapps/
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]