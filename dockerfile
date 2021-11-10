FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-2.9-SNAPSHOT.war timesheet-2.9-SNAPSHOT.war
ENTRYPOINT ["java", "-jar", "/timesheet-2.9-SNAPSHOT.war" ]