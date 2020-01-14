FROM bash:4.4

COPY --from=lachlanevenson/k8s-kubectl:v1.15.5 /usr/local/bin/kubectl /usr/local/bin/kubectl

COPY script.sh /

ENV VERSION=3.3

CMD ["bash", "/script.sh"]
