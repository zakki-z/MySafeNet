FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# We'll mount the following at runtime:
# - target/classes (compiled source)
# - target/dependencies (exploded JAR dependencies)

# The application will run using classpath instead of jar
CMD ["java", "-cp", "classes:dependencies/*", "com.chat.chatapp.ChatappApplication"]


#FROM eclipse-temurin:21-jdk-alpine
#
#WORKDIR /app
#
##RUN ./mvnw clean package -DskipTests
#
#COPY target/*.jar app.jar
#
#EXPOSE 8083
#
#ENTRYPOINT ["java","-jar","/app/app.jar"]
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
