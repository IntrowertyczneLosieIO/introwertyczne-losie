FROM openjdk:11
ADD backend/target/introwertyczne-losie-0.0.1-SNAPSHOT.jar introwertyczne-losie-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","introwertyczne-losie-0.0.1-SNAPSHOT.jar"]