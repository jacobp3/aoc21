import numpy as np

map = np.zeros([1000,1000])
count = 0

def paintArray(m,n):
  global map
  global count
  m0 = int(m[0])
  m1 = int(m[1])
  n0 = int(n[0])
  n1 = int(n[1])
  if(m0 == n0):
    if(m1 > n1):
      y0,y1 = n1,m1
    else:
      y0,y1 = m1,n1
    for y in range(y0,y1+1):
      if(map[m0][y] == 0):
        map[m0][y] = 1
      elif (map[m0][y] == 1):
        count+=1
        map[m0][y] = 2
  elif(m1 == n1):
    if(m0 > n0):
      x0,x1 = n0,m0
    else:
      x0,x1 = m0,n0
    for x in range(x0,x1+1):      
      if(map[x][m1] == 0):
        map[x][m1] = 1
      elif (map[x][m1] == 1):
        count+=1
        map[x][m1] = 2

def readVents():
  global count
  with open('day5.txt') as f:
    lines = f.readlines()
  for line in lines:
    a,b = line.split(' -> ')
    paintArray(a.strip().split(','),b.strip().split(','))
  print(count)

readVents()
