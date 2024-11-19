FROM alpine:3.18
ENV CONSUL_VERSION=1.20.0

RUN apk add --no-cache curl
RUN apk add --no-cache unzip
RUN curl -L -o /tmp/consul.zip "https://releases.hashicorp.com/consul/${CONSUL_VERSION}/consul_${CONSUL_VERSION}_linux_amd64.zip"
RUN unzip /tmp/consul.zip -d /usr/local/bin/
RUN rm /tmp/consul.zip
RUN apk del unzip

EXPOSE 8500
ENTRYPOINT ["consul"]
