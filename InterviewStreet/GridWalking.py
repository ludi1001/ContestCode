"""
Grid Walking (Score 50 points) 
You are situated in an N dimensional grid at position(x_1,x2,...,x_N). The dimensions of the grid are (D_1,D_2,...D_N). In one step, you can walk one step ahead or behind in any one of the N dimensions. (So there are always 2N possible different moves). In how many ways can you take M steps such that you do not leave the grid at any point ? You leave the grid if you for any x_i, either x_i <= 0 or x_i > D_i.

Input:
The first line contains the number of test cases T. T test cases follow. For each test case, the first line contains N and M, the second line contains x_1,x_2...,x_N and the 3rd line contains D_1,D_2,...,D_N.

Output:
Output T lines, one corresponding to each test case. Since the answer can be really huge, output it modulo 1000000007.

Constraints: 1 <= T <= 10
1 <= N <= 10
1 <= M <= 300
1 <= D_i <= 100
1 <= x_i <= D_i

Sample Input #00:
10
1 287
44
78
1 236
25
87
1 122
41
63
1 260
7
64
1 127
3
73
1 69
6
68
1 231
14
63
1 236
13
30
1 259
38
70
1 257
11
12
Sample output: 
38753340
587915072
644474045
423479916
320130104
792930663
846814121
385120933
60306396
306773532
"""
def generate_possibilities(start, dim, steps):
	arr = [0]*dim
	arr_next = [0]*dim
	arr[start] = 1
	check_first = (start % 2 == 0)
	check_last = ((dim - start - 1) % 2 == 0)
	possibilities = [1]
	for i in xrange(steps):
		start_index = 1
		if check_first:
			arr_next[1] = arr[0]
			arr[0] = 0
			start_index = 2
		if check_last:
			arr_next[dim - 2] += arr[dim - 1]
			arr[dim - 1] = 0
			
		for j in xrange(start_index, dim - 1, 2):
			arr_next[j - 1] += arr[j]
			arr_next[j + 1] += arr[j]
			arr[j] = 0
			
		temp = arr
		arr = arr_next
		arr_next = temp
		
		check_first = not check_first
		check_last = not check_last
		
		start_index = 1
		if check_first:
			start_index = 0
		s = 0
		for j in xrange(start_index, dim, 2):
			s += arr[j]
		s %= MOD
		possibilities.append(s)
	return possibilities	
	
def do_grid_walk(dims, steps):
	if dims < 2:
		return possibilities[0][steps]
	p = possibilities[0]
	p_next = []
	for j in xrange(dims - 2):
		for k in xrange(steps + 1):
			s = 0
			choose = 1
			for i in xrange(k + 1):
				res = choose % MOD
				res *= possibilities[j + 1][i] * p[k - i]
				res = res % MOD
				choose = (choose * (k - i))/(i + 1)
				s += res
				s %= MOD
			p_next.append(s)
		p = p_next
		p_next = []
	s = 0
	choose = 1
	for i in xrange(steps + 1):
		res = choose % MOD
		res *= possibilities[dims - 1][i] * p[steps - i]
		res = res % MOD
		choose = (choose * (steps - i))/(i + 1)
		s += res
		s %= MOD
	return s


MOD = 1000000007

for test_case in xrange(int(raw_input())):
	N, M = (int(x) for x in raw_input().split())
	coords = [int(x) - 1 for x in raw_input().split()]
	dims = [int(x) for x in raw_input().split()]
	i = 0
	while i < N:
		if dims[i] == 1:
			del dims[i]
			del coords[i]
			N -= 1
		else:
			i += 1
	if N == 0:
		print 0
		continue
	possibilities = []
	for i in xrange(N):
		possibilities.append(generate_possibilities(coords[i], dims[i], M))
	print do_grid_walk(N, M)