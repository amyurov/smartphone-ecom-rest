FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/smartphone-ecom-app.jar /app/smartphone-ecom-app.jar

ENTRYPOINT ["java", "-jar", "smartphone-ecom-app.jar"]
