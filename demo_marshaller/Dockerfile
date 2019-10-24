FROM bash:4.4

COPY --from=lachlanevenson/k8s-kubectl:v1.15.5 /usr/local/bin/kubectl /usr/local/bin/kubectl

COPY script.sh /
COPY *.yaml /

CMD ["bash", "/script.sh"]
