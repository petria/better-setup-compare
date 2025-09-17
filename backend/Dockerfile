# Use a slim Java
FROM openjdk:24

# Copy the packaged JAR file into the container
COPY target/*.jar /app.jar

# Expose the port the backend runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
