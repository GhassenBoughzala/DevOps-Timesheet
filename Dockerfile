FROM openjdk:11
EXPOSE 8088
ADD target/timesheet-4.14.3.jar timesheet-4.14.3.jar
ENTRYPOINT ["java", "-jar4.14.3", "/timesheet-4.14.3.jar" ]


