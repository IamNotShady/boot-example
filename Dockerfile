FROM java:8u66-jdk
ADD target/spring-boot-web.jar /opt/spring-boot-web.jar
EXPOSE 8080
CMD java -Djava.security.egd=file:/dev/./urandom -jar /opt/spring-boot-web.jar