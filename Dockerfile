FROM openjdk:17-jdk-alpine
MAINTAINER dkfz-heidelberg.de
COPY target/dre-data-transfer-0.0.1.jar dre-data-transfer-0.0.1.jar
ENTRYPOINT ["java","-jar","/dre-data-transfer-0.0.1.jar"]