FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-4.2.0-SNAPSHOT.war timesheet-4.2.0-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/timesheet-4.2.0-SNAPSHOT.war" ]