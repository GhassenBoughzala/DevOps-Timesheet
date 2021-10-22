FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD /target/timesheet-2.4.1-SNAPSHOT.jar timesheet-2.4.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "timesheet-2.4.1-SNAPSHOT.jar" ]