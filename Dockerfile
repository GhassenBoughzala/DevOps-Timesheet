FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-3.1.jar timesheet-3.1.jar
ENTRYPOINT ["java", "-jar", "/timesheet-3.1.jar" ]