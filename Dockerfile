FROM eclipse-temurin:17

LABEL mentainer="olhachirva@gmail.com"

WORKDIR /app

COPY target/springboot-rest-api-with-mysql-0.0.1-SNAPSHOT.jar /app/springboot-rest-api-with-mysql.jar

ENTRYPOINT ["java", "-jar", "springboot-rest-api-with-mysql.jar"]