FROM openjdk:11
EXPOSE 8088
<<<<<<< HEAD
ADD target/timesheet-2.2.3-SNAPSHOT.jar timesheet-2.2.3-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.2.3-SNAPSHOT.jar" ]
=======
ADD target/timesheet-2.2.2-SNAPSHOT.jar timesheet-2.2.2-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/timesheet-2.2.2-SNAPSHOT.jar" ]
>>>>>>> 165da31d78c8232ef9cd2f7d203d106f27f85b5a
