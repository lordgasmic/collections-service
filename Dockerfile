FROM lordgasmic/jre14
WORKDIR /images
WORKDIR /app

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
