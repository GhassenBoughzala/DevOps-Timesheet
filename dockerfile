FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-2.9-SNAPSHOT.jar timesheet-2.9-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.9-SNAPSHOT.jar" ]