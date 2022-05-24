FROM openjdk:14
EXPOSE 8083
ADD ./target/api-scheduler-0.0.1-SNAPSHOT.jar api-scheduler-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","api-scheduler-0.0.1-SNAPSHOT.jar"]