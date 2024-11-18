FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean test package

FROM openjdk:21
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

#FROM openjdk:21
#COPY target/todo-0.0.1-SNAPSHOT.jar todo.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","todo.jar"]