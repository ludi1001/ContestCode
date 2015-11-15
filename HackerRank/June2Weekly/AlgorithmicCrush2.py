def create_tree(begin, end):
    if begin == end:
        return None
    if begin + 1 == end:
        return [begin, end, 0, None, None, 0]
    mid = (begin + end) / 2
    left = create_tree(begin, mid)
    right = create_tree(mid, end)
    return [begin, end, 0, left, right, left[5] if left[5] > right[5] else right[5]]
def update_tree(node, a, b, k, k2):
    if node is None:
        return
    node[2] += k2
    node[5] += k2
    if node[0] >= a and node[1] <= b:
        node[2] += k
        node[5] += k
    elif node[1] <= a or node[0] >= b:
        return
    else:
        update_tree(node[3], a, b, k, node[2])
        update_tree(node[4], a, b, k, node[2])
        node[2] = 0
        node[5] = node[3][5] if node[3][5] > node[4][5] else node[4][5]
N,M = (int(x) for x in raw_input().split())
tree = create_tree(0, N)
for i in xrange(M):
    a,b,k = (int(x) for x in raw_input().split())
    a -= 1
    b -= 1
    update_tree(tree, a, b + 1, k, 0)
    #print(tree)
print(tree[5])