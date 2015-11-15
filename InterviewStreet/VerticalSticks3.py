T = int(raw_input())
for test_case in range(T):
	N = int(raw_input())
	lengths = [int(x) for x in raw_input().split()]
	lengths.sort()
	e = 0
	duplicates = 0
	for i in range(N):
		s = 0
		n = float(i)
		if i > 0 and lengths[i - 1] == lengths[i]:
			duplicates += 1
		else:
			duplicates = 0
		n -= duplicates
		prod = 1
		for k in range(N - 1):
			prod *= (n - k)/(N - 1 - k)
			s += (N - 1 - k) * prod
		s = s/N + 1
		e += s
	print "%0.2f" % e