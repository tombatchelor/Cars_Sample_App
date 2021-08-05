import time

array = []
while True:
  innerArray = []
  for x in range(100000):
    innerArray.append("This is a string I'm adding to an array")
  array.append(innerArray)
  time.sleep(1)