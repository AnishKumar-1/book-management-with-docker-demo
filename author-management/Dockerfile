FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/author-management-0.0.1-SNAPSHOT.jar author-management.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "author-management.jar"]