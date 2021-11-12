FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-4.3.war timesheet-4.3.war
ENTRYPOINT ["java", "-jar", "/timesheet-4.3.war" ]