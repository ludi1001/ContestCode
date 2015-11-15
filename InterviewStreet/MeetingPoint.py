#Note that this is actually only an approximation...
import math
def norm(x1,y1,x2,y2):
	return math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1))

N = int(raw_input())
x = []
y = []
for i in range(N):
	coord = raw_input().split()
	x.append(int(coord[0]))
	y.append(int(coord[1]))
centroid_x = float(sum(x))/N
centroid_y = float(sum(y))/N

min_dist = norm(centroid_x, centroid_y, x[0], y[0])
min_index = 0
for i in range(1, N):
	dist = norm(centroid_x, centroid_y, x[i], y[i])
	if dist < min_dist:
		min_dist = dist
		min_index = i

total_dist = 0
for i in range(N):
	total_dist += max(abs(x[min_index] - x[i]), abs(y[min_index] - y[i]))
print int(total_dist)

