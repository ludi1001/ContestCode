"""
Atul is into graph theory, and he is learning about trees nowadays. He observed that the removal of an edge from a given tree T will result in formation of two separate trees T1 and T2.

Each vertex of the tree T is assigned a positive integer. Your task is to remove an edge, such that, the Tree_diff of the resultant trees is minimized. Tree_diff is defined as

 F(T) = Sum of numbers written on each vertex of a Tree T
 Tree_diff(T) = abs(F(T1) - F(T2))
Input Format
The first line will contain an integer N, i.e., the number of vertices in the tree.
The next line will contain N integers separated by a single space, i.e., the values assigned to each of the vertices.
The next (N-1) lines contain pair of integers separated by a single space and denote the edges of the tree.
In the above input, the vertices are numbered from 1 to N.

Output Format
A single line containing the minimum value of Tree_diff.

Constraints
3 ≤ N ≤ 105
1 ≤ number written on each vertex ≤ 1001

Sample Input

6
100 200 100 500 100 600
1 2
2 3
2 5
4 5
5 6
Sample Output

400
Explanation

Cutting the edge at 1 2 would result in Tree_diff = 1400
Cutting the edge at 2 3 would result in Tree_diff = 1400
Cutting the edge at 2 5 would result in Tree_diff = 800
Cutting the edge at 4 5 would result in Tree_diff = 600
Cutting the edge at 5 6 would result in Tree_diff = 400

Hence, the answer is 400.
"""

#Solution Description
"""
First, we create the tree. Note that the root can be arbitrarily chosen. In this case, we choose the 0th node to be the root. Next, postorder traversal is used to compute the weight of each individual subtree. Finally, we iterate through each edge and compute the tree diff due to removing that edge. We use preorder traversal on the nodes to iterate through the edges. In this case, the edge in consideration is the edge connecting the node under consideration and its parent. The weight of one tree is the weight of the subtree while the other is the weight of the root minus the weight of the subtree. Thus, the tree diff = abs(sum[root] - 2*sum[node]).
"""

#This version uses a stack to avoid default recursion limit (1000)
#Setup stack for postorder traversal. Basic idea is to use preorder traversal to iterate through each node but then push the nodes onto a second stack from which we can just keep popping to reproduce postorder traversal
def setup_real_stack(edge_list):
  stack = [0]
  real_stack = []
  visited = set([0])
  while len(stack) != 0:
    item = stack.pop()
    real_stack.append(item)
    for vert in edge_list[item]:
      if vert not in visited:
        stack.append(vert)
        visited.add(vert)
  return real_stack

#does the actual computing of vertex sum for each subtree
def compute_sum(sums, edge_list, vert_weights):
  real_stack = setup_real_stack(edge_list)
  while len(real_stack) != 0:
    item = real_stack.pop()
    sums[item] = vert_weights[item]
    for vert in edge_list[item]:
        sums[item] += sums[vert]

#preorder traversal to find tree diff        
def min_tree_diff(sums, edge_list):
  stack = [0]
  min_diff = sums[0] #note that the max tree diff is sums[0]
  #this property is very convenient for us because we avoid the awkwardness of having to deal with the root node abs(sums[0]-2*sums[0]) = sums[0] so it will be safely ignored
  visited = set([0])
  while len(stack) != 0:
    item = stack.pop()
    diff = abs(sums[0] - sums[item] - sums[item])
    if diff < min_diff:
      min_diff = diff
    for vert in edge_list[item]:
      if vert not in visited:
        stack.append(vert)
        visited.add(vert)
  return min_diff 

N = int(raw_input())
vert_weights = [int(x) for x in raw_input().split()]
edge_list = [[] for i in range(N)] #adjacency list, since we cannot a priori determine which vertex is the parent during initial parsing
sums = [0]*N #stores weight of each subtree
for i in range(N-1):
  verts = [int(x) for x in raw_input().split()]
  edge_list[verts[0]-1].append(verts[1]-1)
  edge_list[verts[1]-1].append(verts[0]-1)

compute_sum(sums, edge_list, vert_weights)
print(min_tree_diff(sums, edge_list))