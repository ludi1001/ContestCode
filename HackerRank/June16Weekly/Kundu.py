"""
The idea is to merge all the vertices connected by black vertices together because vertices connected by black edges cannot be part of a triplet.
Once we have form these clusters, a triplet can be formed by picking 3 vertices from 3 different clusters. This can be computed as the sum of all the possible products
of the cardinality of 3 clusters, e.g. for clusters of size 1, 2, 3, and 5, the number of triplets is given by
1*2*3 + 1*2*5 + 2*3*5

To find the clusters, we utilize the union-find data structure with lazy union and rank-compression. Next we compute the number of vertices in each cluster.
Finally, to compute the number of triplets, we note that for cluster sizes a, b, c, d... the desired sum can be written as
((a+b+c+d+...)^3 - 3(a+b+c+d+...)(a^2 + b^2 + c^2 + d^2 + ...) + 2(a^3 + b^3 + c^3 + d^3))/6
Now, note that a + b + c + d + ... = N
Furthermore, 6^-1 = 166666668 (mod 10**9+7)
so, the sum to calculate is
(N^3 - 3*N*(a^2 + b^2 + c^2 + d^2 + ...) + 2(a^3 + b^3 + c^3 + d^3 + ...))*166666668 modulo 10**9 + 7
"""
def find(leader, e):
    p = e
    while p != leader[p]:
        p = leader[p]
    while e != leader[e]:
        e, leader[e] = leader[e], p
    return p
MOD = 10**9 + 7
MOD_INV_6 = 166666668
N = int(input())
leader = list(range(N))
rank = [0]*N
counts = [1]*N
for i in range(N-1):
    a, b, c = input().split()
    a = int(a) - 1
    b = int(b) - 1
    if c == 'b':
        p = find(leader, a)
        p2 = find(leader, b)
        if rank[p] < rank[p2]:
            leader[p] = p2
            counts[p2] += counts[p]
            counts[p] = 0
        elif rank[p2] < rank[p]:
            leader[p2] = p
            counts[p] += counts[p2]
            counts[p2] = 0
        else:
            leader[p2] = p
            counts[p] += counts[p2]
            counts[p2] = 0
            rank[p] += 1
total_2 = 0
total_3 = 0
for i in range(N):
    total_2 += counts[i]*counts[i]
    total_3 += counts[i]*counts[i]*counts[i]
    total_2 %= MOD
    total_3 %= MOD
total = (N*N*N) % MOD
total -= 3*total_2*N
total %= MOD
total += 2*total_3
total %= MOD
total *= MOD_INV_6
total %= MOD
if total < 0:
    total += MOD
print(total)