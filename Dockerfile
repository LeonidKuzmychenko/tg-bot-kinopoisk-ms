FROM openjdk:11
MAINTAINER Leonid
COPY target/kinopoisk-0.1.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "/app.jar"]
