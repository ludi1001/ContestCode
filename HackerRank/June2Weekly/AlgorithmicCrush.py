#idea is to use segment tree with lazy propagation to do updates
def create_tree(begin, end):
    if begin == end:
        return None
    if begin + 1 == end:
        return [begin, end, 0, None, None]
    mid = (begin + end) / 2
    left = create_tree(begin, mid)
    right = create_tree(mid, end)
    return [begin, end, 0, left, right]
def update_tree(node, a, b, k):
    if node is None:
        return
    if node[0] >= a and node[1] <= b:
        node[2] += k
    elif node[1] <= a or node[0] >= b:
        return
    else:
        update_tree(node[3], a, b, k)
        update_tree(node[4], a, b, k)
def max_value(node):
    if node is None:
        return 0
    return node[2] + max(max_value(node[3]), max_value(node[4]))    
N,M = (int(x) for x in raw_input().split())
tree = create_tree(0, N)
for i in xrange(M):
    a,b,k = (int(x) for x in raw_input().split())
    a -= 1
    b -= 1
    update_tree(tree, a, b + 1, k)
print(max_value(tree))