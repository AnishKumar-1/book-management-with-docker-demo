FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/book-management-0.0.1-SNAPSHOT.jar book-management.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "book-management.jar"]