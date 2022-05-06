FROM openjdk:14.0.2-oracle
WORKDIR /vol
WORKDIR /app

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
