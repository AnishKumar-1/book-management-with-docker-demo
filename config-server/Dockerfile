FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/config-server-0.0.1-SNAPSHOT.jar config-server.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "config-server.jar"]