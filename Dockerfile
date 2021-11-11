FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-2.2.jar timesheet-2.2.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.2.jar" ]