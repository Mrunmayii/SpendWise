FROM openjdk:17
WORKDIR /app
COPY target/ExpenseTracker-0.0.1-SNAPSHOT.jar app.jar

ENV DB_USER=${DB_USER}
ENV DB_PASS=${DB_PASS}
ENV JWT_SECRET=${JWT_SECRET}

ENTRYPOINT ["java", "-jar", "app.jar"]
