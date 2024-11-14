FROM openjdk:21
ARG JAR_FILE=target/todo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} todo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","todo.jar"]