FROM adoptopenjdk/openjdk11:latest
EXPOSE 5000
ADD /target/timesheet-2.2.5-SNAPSHOT.jar timesheet-2.2.5-SNAPSHOT.jar
ENTRYPOINT ["java", "jar", "timesheet-2.2.5-SNAPSHOT.jar" ]