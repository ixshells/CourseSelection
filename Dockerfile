FROM openjdk:latest
VOLUME /tmp
COPY build/libs/CourseSelection-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
