FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-4.14.4-SNAPSHOT.jar timesheet-4.14.4-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-4.14.4-SNAPSHOT.jar" ]