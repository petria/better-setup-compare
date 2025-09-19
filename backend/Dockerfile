# Build stage
FROM maven:3.9-eclipse-temurin-24 AS build
WORKDIR /app
COPY backend/pom.xml .
COPY frontend ./frontend
COPY backend/src ./src
RUN --mount=type=cache,id=m2,target=/root/.m2 mvn clean package -DskipTests -Pdocker-build

# Runtime stage
FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]