FROM openjdk:11
ADD target/UrlApp.jar UrlApp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","UrlApp.jar"]