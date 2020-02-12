import requests
import os
import sys

collectorURL = os.environ['COLLECTOR_URL']
customerID = os.environ['CUSTOMER_ID']
token = os.environ['TOKEN']
payloadFileName = sys.argv[1]
path = sys.argv[2]

payloadFile = open(payloadFileName, "r")
payload = payloadFile.read()

headers = {
  'Content-Type':'application/json',
  'Authorization':'Bearer ' + customerID + ' ' + token
}

response = requests.post(collectorURL + path, data=payload, headers=headers)

print(response)
