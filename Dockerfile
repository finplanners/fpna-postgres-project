#define base docker image
# FROM openjdk:11
# LABEL maintainer="msciq"
# ADD target/storage-0.0.1-SNAPSHOT.jar storage.jar
# ENTRYPOINT ["java", "-jar", "storage.jar"]

# FROM maven:3.8.4-openjdk-17 as build
# COPY src /usr/src/app/src
# COPY pom.xml /usr/src/app
# RUN mvn -X -f /usr/src/app/pom.xml clean package

FROM openjdk:17
COPY /target/storage*.jar  /usr/app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/usr/app/app.jar"]
