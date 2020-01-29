# we are extending everything from tomcat:8.0 image ...
FROM tomcat:8.5-jdk12-openjdk-oracle
MAINTAINER your_name
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY target/Cars_Sample_App.war /usr/local/tomcat/webapps/

# Added to support Redisson, it can't pull these from WEB-APP/lib for some reason
COPY target/Cars_Sample_App/WEB-INF/lib/jackson-core-2.9.10.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/jackson-dataformat-yaml-2.9.10.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/jackson-annotations-2.9.9.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/jackson-databind-2.9.9.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/redisson-3.11.5.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/redisson-tomcat-8-3.11.5.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/snakeyaml-1.23.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-buffer-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-codec-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-codec-dns-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-common-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-handler-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-resolver-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-resolver-dns-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/netty-transport-4.1.42.Final.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/slf4j-api-1.7.25.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/fst-2.57.jar /usr/local/tomcat/lib/
COPY target/Cars_Sample_App/WEB-INF/lib/objenesis-2.5.1.jar /usr/local/tomcat/lib/

# This set is the leak short circuits to no-op or not, if TRUE leak occures
ENV LEAKING=TRUE
