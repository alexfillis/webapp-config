# webapp-config
Demonstrates how to load configuration in webapp / war file context

Setup demo

Build project

./gradlew clean war

Tomcat

1. Copy war file to <tomcat-base>/webapps
2. Copy config/local/webapp-config-1.0-SNAPSHOT.xml <tomcat-base>/conf/Catalina/localhost/
3. Edit <tomcat-base>/conf/Catalina/localhost/config/local/webapp-config-1.0-SNAPSHOT.xml and provide base part of the file names under property 'config.file'.
4. Open browser and goto http://localhost:8080/webapp-config-1.0-SNAPSHOT/config

Note: Tested with Apache Tomcat 7.0.62
