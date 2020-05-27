FROM maven:3.6.3-jdk-11 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src

RUN mvn clean package && cp target/app.jar app.jar

FROM openjdk:11.0.7-jre-buster
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]