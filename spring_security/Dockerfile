FROM openjdk:17

WORKDIR /app

COPY /target/spring_security.jar /app/spring_security.jar

# Expose the port your Spring Boot application runs on (default is 8080)
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "spring_security.jar"]