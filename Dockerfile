### Build stage: compile the project and package the fat jar
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copy only what we need for a maven build to leverage caching
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

# Make mvnw executable if present
RUN if [ -f mvnw ]; then chmod +x mvnw; fi

# Build the project (skip tests for speed)
RUN mvn -B package -DskipTests

### Run stage: use a lightweight JRE image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /
COPY --from=build /workspace/target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
