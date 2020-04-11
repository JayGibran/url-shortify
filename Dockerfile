FROM openjdk:8-jdk-alpine
MAINTAINER Jay Gibran

EXPOSE 8080
WORKDIR /usr/local/bin
COPY target/url-shortify-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-Dspring.profiles.active=container", "-jar", "app.jar"]