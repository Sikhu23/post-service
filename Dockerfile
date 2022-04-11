FROM openjdk:17
ADD target/dockerPostService.jar dockerPostService.jar
EXPOSE 3010
ENTRYPOINT ["java","-jar","dockerPostService.jar"]