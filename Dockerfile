FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD target/timesheet4.14.3-SNAPSHOT.war timesheet4.14.3-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/timesheet4.14.3-SNAPSHOT.war" ]