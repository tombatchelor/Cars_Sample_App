# Cron Cleanup Readme

This is a Python script that runs every 12 hours as a CronJob in Kubernetes. It scans the CARS and ENQUIRIES tables to trim them. 

If either of these tables as more than 3000 rows it trims back to 1000 rows. It does this by picking rows at random to delete to closer simulate something real world.

`clean_up.py` is the script and `Dockerfile` is the container definition