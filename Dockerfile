FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD target/timesheet-2.6.2-SNAPSHOT.jar timesheet-2.6.2-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.6.2-SNAPSHOT.jar" ]