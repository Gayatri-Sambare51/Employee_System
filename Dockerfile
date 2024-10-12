# First stage: build the application
FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

# Second stage: create a minimal image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/target/Employee_System-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
