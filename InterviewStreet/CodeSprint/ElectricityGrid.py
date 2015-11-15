"""

You are in a planning team, which is in charge of installing an auxilary electricity grid. The purpose of this auxilary grid is to power the lampposts on junctions of a small town in case of power outage. Your team is given K generators. You can place these generators anywhere in the gird and each generator can turn on all the lamps that have a connection to it via the grid. For each road in the town, you are given the cost of laying down a cable in the grid to connect the two junctions at the endpoints of that road. Given the layout of the town, your task is to lay down the minimum cost grid and install the generators on it such that you can turn on all the lampposts on the junctions of the town.

Input:
The first line contains the number of cases T. Then, T cases follow. The first line of each case contains three integer N, M, and K. The junctions are numbered 1 to N. 
Then, M lines follows. Each line contains three integers I, J, C. The integers I and J are between 1 and N and denote the two junctions at the two endpoints of a road. The third integer, C, is the cost of laying down a cable in the grid on this road.

Output:
You should output T lines, one for each case. For each case output the minimum cost grid. If this task is impossible (i.e. there is no way to turn all the lamps on with K generators), output "Impossible!".

Constraints:
1 <= T <= 5
1 <= N <= 2000
0 <= M <= N(N-1)/2
0 <= C <= 1000000

Sample Input:
2
3 1 1
1 2 10
6 7 2
1 2 20
1 3 5
1 4 10
2 3 8
2 4 15
3 4 2
5 6 9

Sample Output:
Impossible!
24

Explanation:
In the first case, the junctions 1 and 2 are disconnected from junction 3 and you cannot turn on all the lampposts with only one generator. You need at least two generators.
In the second case, you can lay down cable on the roads (1 3), (2 3), (3 4), and (5 6) and then install one generator on junction 1 and one generator on junction 5.
"""
def find(v):
	for i in xrange(components):
		if v in forest[i]:
			return i

test_cases = int(raw_input())
for test_case in xrange(test_cases):
	(N,M,K) = (int(x) for x in raw_input().split())
	#if K == 0:
	#	print "Impossible!"
	#	continue
	#if M >= K:
	#	print 0
	#	continue
	edges = []
	for counter in xrange(M):
		(i,j,cost) = (int(x) for x in raw_input().split())
		edges.append((cost, i - 1, j - 1))
	edges.sort()
	forest = []
	for i in xrange(N):
		s = set()
		s.add(i)
		forest.append(s)
	components = N
	
	cost = 0
	for e in edges:
		i1 = find(e[1])
		i2 = find(e[2])
		if i1 != i2:
			cost += e[0]
			s1 = forest[i1]
			s2 = forest[i2]
			del forest[i1]
			if i1 < i2:
				i2 -=1
			del forest[i2]
			forest.append(s1 | s2)
			components -= 1
			if components <= K:
				print cost
				break
	if components > K:
		print "Impossible!"