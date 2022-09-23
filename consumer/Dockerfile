FROM openjdk:17-jdk-alpine
MAINTAINER offerista.com
RUN apk add --no-cache bash
COPY target/consumer-0.0.1-SNAPSHOT.jar consumer-0.0.1-SNAPSHOT.jar
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh