FROM adoptopenjdk/openjdk11:latest
EXPOSE 5000
ADD /target/timesheet-2.3.0-SNAPSHOT.jar timesheet-2.3.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "timesheet-2.3.0-SNAPSHOT.jar" ]