FROM bash:4.4
COPY --from=lachlanevenson/k8s-kubectl:v1.16.9 /usr/local/bin/kubectl /usr/local/bin/kubectl

COPY script.sh /
COPY goodmap.yaml /
COPY badmap.yaml /

CMD ["bash", "/script.sh"]
