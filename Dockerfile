FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-2.1.2-SNAPSHOT.jar timesheet-2.1.2-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.1.2-SNAPSHOT.jar" ]