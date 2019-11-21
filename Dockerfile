FROM maven:3.6.1-jdk-11-slim as build-env
WORKDIR /app
COPY pom.xml pom.xml

COPY . /app

RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml clean install

FROM openjdk:11.0.3-slim-stretch

COPY --from=build-env /app/target/*.jar /application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-XX:MinRAMPercentage=50.0",  "-XX:MaxRAMPercentage=80.0", "-jar", "/application.jar"]
