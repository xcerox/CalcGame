FROM alpine:3.18
ENV CONSUL_VERSION=1.20.0
RUN apk add --no-cache curl unzip && \
    curl -L -o /tmp/consul.zip "https://releases.hashicorp.com/consul/${CONSUL_VERSION}/consul_${CONSUL_VERSION}_linux_amd64.zip" && \
    unzip /tmp/consul.zip -d /usr/local/bin/ && \
    rm /tmp/consul.zip && \
    apk del unzip
EXPOSE 8500
ENTRYPOINT ["consul"]
