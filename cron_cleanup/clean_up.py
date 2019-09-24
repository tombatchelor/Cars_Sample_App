import mysql.connector
import random

def get_array_of_randoms(length, limit):
    randoms = []
    for x in range(length):
        randoms.append(random.randrange(limit))
    randoms = list(dict.fromkeys(randoms))
    return randoms

def trim_table(selectQuery, deleteQuery, max, cnx):
    cursor = cnx.cursor()
    cursor.execute(selectQuery)
    IDs = cursor.fetchall()
    cursor.close()
    
    if len(IDs) > max:
        randoms = get_array_of_randoms(len(IDs) - max, len(IDs))
        print(len(randoms))
        cursor = cnx.cursor()
        # Don't delete car 1 as that is where enquiries get posted
        for x in randoms:
            if x != 0:
                cursor.execute(deleteQuery, IDs[x])
        cnx.commit()
        cursor.close()

config = {
  'user': 'root',
  'password': 'AppDynamics',
  'host': 'mysql',
  'database': 'supercars',
  'raise_on_warnings': True
}

carsQuery = 'SELECT CAR_ID FROM CARS'
carsDelete = 'DELETE FROM CARS WHERE CAR_ID = %s'
enquiryQuery = 'SELECT ENQUIRY_ID FROM ENQUIRIES'
enquiryDelete = 'DELETE FROM ENQUIRIES WHERE ENQUIRY_ID = %s'

maxCars = 3000
maxQueries = 3000

print('Start Cleanup')
cnx = mysql.connector.connect(**config)

trim_table(carsQuery, carsDelete, maxCars, cnx)
print('End CARS cleanup')
print('Start ENQUIRIES cleanup')
trim_table(enquiryQuery, enquiryDelete, maxQueries, cnx)
print('End ENQUIRIES cleanup')

cnx.close()
