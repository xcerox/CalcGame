FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY ../logs/pom.xml ./
RUN mvn dependency:resolve
COPY ../logs/src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17.0.3_7-jre-focal@sha256:f25a044d2b9c6c506ea53ffd0971c33dbde92615b2cad0d9a7284a1b7dc82f4d
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8580
VOLUME /app/logs
ENTRYPOINT ["java", "-jar", "app.jar"]