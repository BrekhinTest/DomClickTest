FROM openjdk:11-jre-slim

RUN apt-get update

COPY target/*.jar /application.jar

ENTRYPOINT ["java", "-jar", "/application.jar", "--spring.profiles.active=prod"]
