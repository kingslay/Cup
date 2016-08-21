FROM alpine:latest
VOLUME /tmp
RUN apk --update add mysql  openjdk7-jre openssh bash
ADD spring-boot-cup-1.1.2.jar app.jar
CMD ["mysql"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
