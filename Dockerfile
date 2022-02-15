FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-4.2.jar timesheet-4.2.jar
ENTRYPOINT ["java", "-jar", "/timesheet-4.2.jar" ]