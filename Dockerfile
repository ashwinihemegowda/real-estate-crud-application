FROM openjdk:8
ADD target/realestatepro-0.0.1-SNAPSHOT.jar realestatepro-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "realestatepro-0.0.1-SNAPSHOT.jar"]
EXPOSE 8191