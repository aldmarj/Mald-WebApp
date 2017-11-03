FROM openjdk:8u141-jdk-slim
VOLUME /tmp
COPY /target/website.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]