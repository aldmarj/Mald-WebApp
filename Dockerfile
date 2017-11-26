FROM openjdk:8u141-jdk-slim
VOLUME /tmp
COPY /target/website.war app.war
ENTRYPOINT exec java $JAVA_OPTIONS -Djava.security.egd=file:/dev/./urandom -jar /app.war