FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD target/timesheet-3.0-SNAPSHOT.jar timesheet-3.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-3.0-SNAPSHOT.jar" ]