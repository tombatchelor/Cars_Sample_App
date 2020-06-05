# Demo Marshaller

This Dockerfile just executes kubectl and has 4 potential config it applies. The configs will

* Change the size of the cars-deployment
* Change the container used by the cars-deployment to leaking or non-leaking

The cycle is:

* 0-1 hours - 2 Pods, leaking
* 1-2 hours - 4 Pods, leaking
* 2-3 hours - 4 Pods, non-leaking
* 3-4 hours - 2 Pods, non-leaking

The cycling of the above is controlled by CronJobs in the main YAML file in [kubernetes](../kubernetes/) 