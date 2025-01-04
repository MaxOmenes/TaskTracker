FROM amazoncorretto:21-alpine-jdk
LABEL authors="MaxOmenes"

WORKDIR /app
COPY target/tasktracker-0.0.1-SNAPSHOT.jar /app/tasktracker.jar
CMD ["java", "-jar", "tasktracker.jar"]
