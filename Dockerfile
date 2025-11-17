FROM --platform=$TARGETOS/$TARGETARCH eclipse-temurin:25-jre-alpine

WORKDIR /usr/app
COPY build/install/telegram-deppgpt .

ENTRYPOINT ["/usr/app/bin/telegram-deppgpt"]
