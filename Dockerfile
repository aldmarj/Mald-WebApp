FROM openjdk:8u141-jdk-slim
VOLUME /tmp
ADD /target/website.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]