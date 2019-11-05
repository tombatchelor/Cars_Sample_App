#!/bin/bash

if [ "$NEW_YAML" == "2_leak" ]
then
    LEAK="-leak"
    SIZE=2
fi

if [ "$NEW_YAML" == "4_leak" ]
then
    LEAK="-leak"
    SIZE=4
fi

if [ "$NEW_YAML" == "4_no_leak" ]
then
    LEAK=
    SIZE=4
fi

if [ "$NEW_YAML" == "2_no_leak" ]
then
    LEAK=""
    SIZE=2
fi

echo kubectl set image deployment/cars-deployment app-server=tombatchelor/cars:$VERSION$LEAK
kubectl set image deployment/cars-deployment app-server=tombatchelor/cars:$VERSION$LEAK
echo kubectl scale --replicas=$SIZE deployment/cars-deployment
kubectl scale --replicas=$SIZE deployment/cars-deployment