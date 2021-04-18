FROM openjdk:11-jre
MAINTAINER Morozov Andrey

ADD ./target/reckoning.jar /app/
RUN mkdir /app/images
CMD ["java", "-Xms200m", "-Xmx500m", "-jar", "/app/reckoning.jar"]

EXPOSE 6000
