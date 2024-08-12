# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the backend code into the container
COPY . .

# Build the backend application (if using Gradle)
RUN ./gradlew build

# Expose the port that the app runs on
EXPOSE 8080

# Run the backend application
CMD ["java", "-jar", "build/libs/vinyl-store-backend-0.0.1-SNAPSHOT.jar"]
