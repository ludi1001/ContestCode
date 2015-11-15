"""
The main idea here is to keep track of the number of times each element of the tree appears on even and odd levels.
Note that technically you only need to keep track for one of the two levels (even or odd) since the sum of even and odd should be catalan,
but it's easy to keep track of both and was one way of checking the code (i.e. that even + odd = catalan).

In the code and following explanation, counts is an array with indices referring to the tree size, the node, and even/odd counts:
counts[tree size][node][even/odd (even = 0, odd = 1)]

To start, we sort the array of numbers in ascending order.
To compute the counts, we iterate through tree sizes, from 1 to N.
For each tree size i, we iterate through all the possible root elements j.
Since the array is sorted, all the elements to the left of element j form the left subtree and all the elements to the right of element j form the right subtree.

Let's consider the left subtree. The left subtree has size j.
Now note that the root node of this subtree is now on an odd level and the children of the root node of the subtree are now on even level and so forth.
Effectively, even and odd levels are flipped. Hence, counts[i][k][0] depends on counts[j][k][1] and vice versa
The catalan[i-j-1] factor comes from the fact that the right subtree has size i-j-1 so there are i-j-1 duplicates of the left BST that appear with root j as the root.
The right subtree updates are done similarly.

The root node must be updated by catalan[j]*catalan[i-j-1] because the root node remains invariant for that many trees (# possible left subtrees * # possible right)

Finally note that it is possible to compute catalan directly in the process instead of computing it separately beforehand using the property that even counts + odd counts = catalan.

"""
MOD = 10**9 + 9
def compute_catalan(N):
    global MOD
    catalan = [0]*(N+1)
    catalan[0] = 1
    for i in range(1, N):
        for j in range(i):
            catalan[i] += catalan[j]*catalan[i-j-1]
        catalan[i] %= MOD
    return catalan
def generate_counts(catalan, N):
    counts = [[[0]*2 for j in range(N)] for i in range(N+1)]
    for i in range(1,N+1):
        for j in range(i):
            counts[i][j][0] += catalan[j]*catalan[i-j-1]
            counts[i][j][0] %= MOD
            for k in range(j):
                counts[i][k][0] += counts[j][k][1]*catalan[i-j-1]
                counts[i][k][1] += counts[j][k][0]*catalan[i-j-1]
                counts[i][k][0] %= MOD
                counts[i][k][1] %= MOD
            for k in range(j+1,i):
                counts[i][k][0] += counts[i-j-1][i-k-1][1]*catalan[j]
                counts[i][k][1] += counts[i-j-1][i-k-1][0]*catalan[j]
                counts[i][k][0] %= MOD
                counts[i][k][1] %= MOD
    return counts
catalan = compute_catalan(150)
res = generate_counts(catalan, 150)
T = int(input())
for test_case in range(T):
    N = int(input())
    alpha, beta = (int(x) for x in input().split())
    arr = [int(x) for x in input().split()]
    arr = sorted(arr)
    #res = generate_counts(catalan,N)
    s = 0
    for i in range(N):
        s += (alpha*res[N][i][0] - beta*res[N][i][1])*arr[i]
        s %= MOD
    print(s)
