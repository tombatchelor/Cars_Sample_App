# Kubernetes Readme

This just contains `cars_app.yaml` which defines the deployment of the whole demo to K8s. 

There are a number of deployment defined:

* cars-deployment-web - This is the Nginx layer
* cars-deployment - This is the Tomcat app layer
* cars-db-deployment - This is the MySQL DB, note no persistent storage
* car-insurance-go - The Go insurance service, code for this is in [https://github.com/tombatchelor/car-insurance]()
* cars-prometheus-deployment - This is the prometheus ReplicSet for metrics, there is a volume configured later in the file containing the configuration
* cars-loadgen-deployment - This is the Load Testing ReplicaSet

There is one CronJob configured:

* cleanup - This runs the cron_cleanup every 12 hours

There are a number of services:

* mysql - Exposes the DB internally
* cars-app - Exposes the app server internally
* cars-web - Exposes the web server internally (note load gen is internal to K8s thus the web server is not exposed externally)
* insurance - Exposes the Insurance Service internally

There is one ClusterRole

* prometheus - To enable Prometheus to get cluster data for metric collection

There is one ServiceAccount

* prometheus

There is one ClusterRoleBinding

* prometheus - Binds the Role and Service account

There is one ConfigMap

* prometheus-config - Contains the config.yaml for Prometheus