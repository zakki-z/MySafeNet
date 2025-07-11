FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java","-jar","/app/app.jar"]
#
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
## Stage 1: build the project using Maven in a clean container
#FROM maven:3.9.6-eclipse-temurin-21 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
## Stage 2: run the app with a slim JDK
#FROM eclipse-temurin:21-jdk-alpine
#WORKDIR /app
#COPY --from=builder /app/target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
