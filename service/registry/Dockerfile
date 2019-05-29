FROM java:8-jre
LABEL maintainer="Cuong Nguyen <cuongnguyen2011.94@gmail.com>"

WORKDIR /app

ADD build/libs/*.jar app.jar

ADD wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

EXPOSE 8761

ENTRYPOINT ["sh", "-c", "./wait-for-it.sh -t 60 config:8888 -- java $JAVA_OPTS -Xmx200m -jar app.jar"]