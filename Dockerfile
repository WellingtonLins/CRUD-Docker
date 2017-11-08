FROM tomcat
COPY target/SimpleSevletDB.war ${CATALINA_HOME}/webapps