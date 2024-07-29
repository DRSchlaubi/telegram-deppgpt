FROM eclipse-temurin:22-jre-alpine

WORKDIR /usr/app
COPY build/install/deppgpt-telegram .

ENTRYPOINT ["/usr/app/bin/deppgpt-telegram"]
