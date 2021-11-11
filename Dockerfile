FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-1.1.jar timesheet-1.1.jar
ENTRYPOINT ["java", "-jar", "/timesheet-1.1.jar" ]