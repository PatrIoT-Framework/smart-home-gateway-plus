# Use an official Maven image as the base image
FROM maven:3-openjdk-17 AS build
ADD target/virtual-smart-home-plus-gateway-0.0.1-SNAPSHOT.jar virtual-smart-home-plus-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "virtual-smart-home-plus-gateway-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080