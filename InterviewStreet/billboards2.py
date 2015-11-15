"""
Billboards(20 points)

ADZEN is a very popular advertising firm in your city. In every road you can see their advertising billboards. Recently they are facing a serious challenge , MG Road the most used and beautiful road in your city has been almost filled by the billboards and this is having a negative effect on the natural view.

On people's demand ADZEN has decided to remove some of the billboards in such a way that there are no more than K billboards standing together in any part of the road.

 

You may assume the MG Road to be a straight line with N billboards.Initially there is no gap between any two adjecent billboards.

 

ADZEN's primary income comes from these billboards so the billboard removing process has to be done in such a way that the billboards remaining at end should give maximum possible profit among all possible final configurations.Total profit of a configuration is the sum of the profit values of all billboards present in that configuration.

 

Given N,K and the profit value of each of the N billboards, output the maximum profit that can be obtained from the remaining billboards under the conditions given.

 

Input description 

Ist line contain two space seperated integers N and K. Then follow N lines describing the profit value of each billboard i.e ith line contains the profit value of ith billboard.

Sample Input
6 2 
1
2
3
1
6
10 

Sample Output
21

Explanation

In given input there are 6 billboards and after the process no more than 2 should be together.
So remove 1st and 4th billboards giving a configuration _ 2 3 _ 6 10 having a profit of 21. No other configuration has a profit more than 21.So the answer is 21.

Constraints
1 <= N <= 1,00,000(10^5)
1 <= K <= N
0 <= profit value of any billboard <= 2,000,000,000(2*10^9)

"""
N, K = (int(x) for x in raw_input().split())
value = [0]*(K+1)
kstore = [0]*K
qindex = 0
vindex = 0
m = 0
for i in xrange(1,N+1):
	c = int(raw_input())
	if m < K:
		if vindex == 0:
			value[vindex] = value[K] + c
		else:
			value[vindex] = value[vindex - 1] + c
		m += 1
	else:
		diff = 0
		index = vindex - 1
		if index < 0:
			index = K
		maximum = value[index]
		s = 0
		kindex = qindex
		for j in xrange(1,K+1):
			index -= 1
			if index < 0:
				index = K
			if value[index] + c + s > maximum:
				maximum = value[index] + c + s
				diff = j
			kindex -= 1
			if kindex < 0:
				kindex += K
			s += kstore[kindex]
		m = diff
		value[vindex] = maximum
	kstore[qindex] = c
	qindex += 1
	if qindex >= K:
		qindex = 0
	vindex += 1
	if vindex > K:
		vindex = 0
	#print kstore
	#print value
if vindex == 0:
	print value[K]
else:
	print value[vindex - 1]