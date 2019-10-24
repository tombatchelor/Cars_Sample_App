# we are extending everything from tomcat:8.0 image ...
FROM tomcat:8.5-jdk12-openjdk-oracle
MAINTAINER your_name
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY target/Cars_Sample_App.war /usr/local/tomcat/webapps/

# This set is the leak short circuits to no-op or not, if TRUE leak occures
ENV LEAKING=FALSE
