import time

array = []
while True:
  innerArray = []
  for x in range(25000):
    innerArray.append("This is a string I'm adding to an array")
  print("OOM: I'm appending 25000 items to the array", flush = True)
  array.append(innerArray)
  time.sleep(1)