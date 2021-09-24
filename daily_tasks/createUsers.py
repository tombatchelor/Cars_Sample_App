import csv
import random

firstNames = ["June", "Tom", "Vikram", "Vanessa", "Theo", "Priti", "Ken", "Melissa", "Gunter", "Alison", "John", "Victoria", "Prasad", "Anu", "Aditya", "Tara", "Connor", "Jen", "Raheem", "George", "Marie"]
lastNames = ["Cunningham", "Ford", "Karumbunathan", "Welsh", "Hawley", "Kohler", "Nair", "Zhang", "Lenga", "Peterson", "Bekishhov", "Wong", "Williams", "Reuben", "Stirling", "Rashford", "Russell"]
countries = ["USA", "UK", "India", "Germany", "Japan", "Canada", "France", "Sweden", "France", "South Africa", "Australia"]

companyNames = []
with open('companies.csv') as csvfile:
  companies = csv.reader(csvfile)
  line_count = 0
  for row in companies:
    if line_count != 0:
      companyNames.append(row[0])
    line_count += 1

users = []
emails = []
users.append(["firstname","lastname","email","country","company"])

for company in companyNames:
  userCount = random.randint(10,25)
  for x in range(userCount):
    firstName = firstNames[random.randint(0, len(firstNames))-1]
    lastName = lastNames[random.randint(0, len(lastNames))-1]
    country = countries[random.randint(0,len(countries))-1]
    email = firstName.lower() + "." + lastName.lower() + "@" + company.lower() + ".com"
    users.append([firstName, lastName, email, country, company])
    emails.append([email])

with open('users.csv', mode='w') as user_file:
  user_writer = csv.writer(user_file)
  user_writer.writerows(users)

with open('emails.csv', mode='w') as email_file:
  email_writer = csv.writer(email_file)
  email_writer.writerows(emails)