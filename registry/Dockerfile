FROM java:8-jre
LABEL maintainer="Cuong Nguyen <cuongnguyen2011.94@gmail.com>"

WORKDIR /app

ADD build/libs/*.jar /app.jar

EXPOSE 8761

CMD ["java", "-Xmx200m", "-jar", "/app.jar"]