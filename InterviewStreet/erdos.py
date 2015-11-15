def harmonic(h):
	sum = 0
	for i in range(1,h+1):
		sum += float(1)/(i)
	return sum

from math import log10,floor,log,sqrt
for x in range(2,30):
	print "{0}, {1}".format(round(1.25*x*log10(x)), round(.496*(x-1)*harmonic(x-1)))
	print round(log(x)*(x-1)/2)
	
