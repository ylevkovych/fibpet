FROM alpine:latest

WORKDIR '/app'

RUN apk add --no-cache openjdk17

COPY ./target/fibpet-1.0.war .

CMD java -jar fibpet-1.0.war