def gcd(a,b):
	while b != 0:
		c = b
		b = a % b
		a = c
	return a

N = int(raw_input())
for i in xrange(N):
	(A,B,C) = (int(x) for x in raw_input().split())
	area2 = 0
	if A + B <= C:
		area2 = 2 * A * B
	elif C <= A and C <= B:
		area2 = C * C
	elif C > A and C > B:
		area2 = 2 * A * B - (A + B - C) * (A + B - C)
	elif C <= A and C > B:
		area2 = 2 * B * (C - B) + B * B
	else:
		area2 = 2 * A * (C - A) + A * A
	tarea2 = 2 * A * B
	g = gcd(tarea2, area2)
	num = area2 / g
	den = tarea2 / g
	print str(num) + "/" + str(den)