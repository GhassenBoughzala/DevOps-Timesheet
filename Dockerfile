FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD target/timesheet-3.0.1-SNAPSHOT.war timesheet-3.0.1-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/timesheet-3.0.1-SNAPSHOT.war" ]