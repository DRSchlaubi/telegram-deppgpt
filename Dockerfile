FROM eclipse-temurin:22-jre-alpine

WORKDIR /usr/app
COPY build/install/telegram-deppgpt .

ENTRYPOINT ["/usr/app/bin/telegram-deppgpt"]
