FROM openjdk:17



ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8081
ENTRYPOINT ["top","-b"]

ENTRYPOINT ["java", "-jar" ,"/app.jar"]