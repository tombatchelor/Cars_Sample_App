# JMeter Load Test Readme

This is the load testing for the app. Note that no external access to the app is required for load testing to occur, it is based on internal Kubernetes services.

The load testing is performed by JMeter, with `SuperCars.jmx` describing the load, right now just a single thread. `Dockerfile` describes the load testing container.