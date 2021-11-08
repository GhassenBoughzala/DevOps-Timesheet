FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD target/*.jar timesheet-2.7.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.7.0-SNAPSHOT.jar"]