# Web Server Readme

This contains the config and container definition for the front end web server.

`nginx.conf` defines the reverse proxy, connecting to `cars-app` which is the service definition in [../kubernetes/cars_app.yaml]()

There is also configuration for Zipkin tracing through the OpenTrace API, the `zipkin-config.json` file defines the Zipkin configuration.

`Dockerfile` is the container defintion for this component