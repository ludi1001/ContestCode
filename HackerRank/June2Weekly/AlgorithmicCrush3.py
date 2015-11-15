#segment tree with lazy propagation, maximum computed during updates, nodes created lazily to reduce memory
#also note that this doesn't actually solve the challenge in time.
def update_tree(node, a, b, k, k2):
    node[2] += k2
    node[5] += k2
    if node[0] >= a and node[1] <= b:
        node[2] += k
        node[5] += k
    elif node[1] <= a or node[0] >= b:
        return
    else:
        if node[3] is None:
            mid = (node[0] + node[1]) >> 1
            node[3] = [node[0], mid, 0, None, None, 0]
            node[4] = [mid, node[1], 0, None, None, 0]
        update_tree(node[3], a, b, k, node[2])
        update_tree(node[4], a, b, k, node[2])
        node[2] = 0
        node[5] = node[3][5] if node[3][5] > node[4][5] else node[4][5]
N,M = (int(x) for x in raw_input().split())
tree = [0, N, 0, None, None, 0]
for i in xrange(M):
    a,b,k = (int(x) for x in raw_input().split())
    a -= 1
    b -= 1
    update_tree(tree, a, b + 1, k, 0)
print(tree[5])