FROM openjdk:17

EXPOSE 8080

ADD target/demo.jar demo.jar

#ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

#COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/demo.jar"]
