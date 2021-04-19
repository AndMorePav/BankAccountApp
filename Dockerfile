FROM maven:3.6.0-jdk-8-slim
MAINTAINER Morozov Andrey

COPY ./ ./

RUN mvn clean compile package

CMD ["java", "-Xms200m", "-Xmx500m", "-jar", "target/reckoning-0.0.1-SNAPSHOT.jar"]

EXPOSE 6000
