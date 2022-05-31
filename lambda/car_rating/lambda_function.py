import json
import pymysql
import rds_config
import base64
import logger
import memory_consumer
import sys

rds_host = rds_config.db_endpoint
name = rds_config.db_username
password = rds_config.db_password
db_name = rds_config.db_name
port = 3306

logger.log("INFO", {'requesType' : 'rating', 'eventType': 'lambdaStarting'})

try:
    conn = pymysql.connect(host=rds_host,user=name,
                           passwd=password,db=db_name,
                           connect_timeout=5,
                           cursorclass=pymysql.cursors.DictCursor)
except:
    sys.exit()
    #print("DB Connection Failed")
def lambda_handler(event, context):
    manufacturerId = event["manufacturerId"]
    traceId = event["traceId"]
    logger.log("INFO", {'requestType': 'rating', 'eventType': 'callStarting', 'manufacturerId': manufacturerId, 'traceId': traceId})
    with conn.cursor() as cur:
        qry = "SELECT RATING as rating FROM MANUFACTURER WHERE MANUFACTURER_ID = " + str(manufacturerId)
        cur.execute(qry)
        body = cur.fetchall()
    rating = body[0]["rating"]
    logger.log("INFO", {'requestType': 'rating', 'eventType': 'gotRating', 'manufacturerId': manufacturerId, 'rating': rating, 'traceId': traceId})
    with conn.cursor() as cur:
        qry = "SELECT CAR_ID, NAME, MODEL, YEAR, PRICE, PHOTO FROM CARS WHERE MANUFACTURER_ID = " + str(manufacturerId)
        cur.execute(qry)
        cars = cur.fetchall()
    logger.log("INFO", {'requestType': 'rating', 'eventType': 'gotCars', 'manufacturerId': manufacturerId, 'carCount':  len(cars), 'traceId': traceId})
    logger.log("DEBUG", {'requestType': 'rating', 'eventType': 'sampleCar', 'manufacturerId': manufacturerId, 'car': cars[0], 'traceId': traceId})
    for car in cars:
      #car["memory"] = memory_consumer.consume_memory(256)
      car["RATING"] = rating
    logger.log("INFO", {'requestType': 'rating', 'eventType': 'readyToReturn', 'manufacturerId': manufacturerId, 'carCount':  len(cars), 'traceId': traceId})
    return {
        'statusCode': 200,
        'headers': {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token',
            'Access-Control-Allow-Credentials': 'true',
            'Content-Type': 'application/json'
        },
        'body': str(json.dumps(body[0])),
        'rating':rating
    }
