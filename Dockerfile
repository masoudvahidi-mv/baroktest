FROM openjdk:17-jdk

WORKDIR /app

COPY target/barok-system-api-0.0.1-SNAPSHOT.jar /app/barok.jar

EXPOSE 8080

CMD ["java","-jar","barok.jar"]