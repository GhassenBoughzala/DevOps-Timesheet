FROM adoptopenjdk/openjdk11:latest
EXPOSE 8088
ADD /target/timesheet-2.7.2-SNAPSHOT.war timesheet-2.7.2-SNAPSHOT.war
ENTRYPOINT [ "java", "-jar", "timesheet-2.7.2-SNAPSHOT.war" ]
