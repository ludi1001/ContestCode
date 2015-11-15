K = int(raw_input())
nums = [int(x) for x in raw_input().split()]
matrix = []
for i in xrange(K):
	matrix.append([0 for x in raw_input() if x == "Y" else 0])
