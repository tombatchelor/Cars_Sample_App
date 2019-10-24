# Demo Marshaller

This Dockerfile just executes kubectl and has 4 potential config it applies. The configs will

* Change the size of the cars-deployment
* Change the container used by the cars-deployment to leaking or non-leaking

The cycle is:

* 0-30 minutes - 2 Pods, leaking
* 30-60 minutes - 4 Pods, leaking
* 60-90 minutes - 4 Pods, non-leaking
* 90-120 minutes - 2 Pods, non-leaking

The cycling of the above is controlled by CronJobs in the main YAML file in [kubernetes](../kubernetes/) 