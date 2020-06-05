#/bin/bash

if [ "$MODE" = "BAD" ]; then
    kubectl apply -f /badmap.yaml
fi

if [ "$MODE" = "GOOD" ]; then
    kubectl apply -f /goodmap.yaml
fi
