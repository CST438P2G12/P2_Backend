# Stage 1: Build the JAR artifact
FROM gradle:jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build

# Stage 2: Create the final runtime image
FROM eclipse-temurin:17
WORKDIR /app
# Copy the JAR file from the 'builder' stage to the 'final' stage
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
