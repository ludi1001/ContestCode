#same thing but minimal function calls
N,M = (int(x) for x in raw_input().split())
tree = [0, N, 0, None, None, 0]
stack = [None]*N
for i in xrange(M):
    a,b,k = (int(x) for x in raw_input().split())
    a -= 1
    b -= 1
    stack[0] = (tree, a, b + 1, k, 0)
    stack_index = 1
    while stack_index > 0:
        stack_index -= 1
        node, a, b, k, k2 = stack[stack_index]
        if k2 < 0:
            node[2] = 0
            node[5] = node[3][5] if node[3][5] > node[4][5] else node[4][5]
            continue
        node[2] += k2
        node[5] += k2
        if node[1] <= a or node[0] >= b:
            continue
        if node[0] >= a and node[1] <= b:
            node[2] += k
            node[5] += k
        else:
            if node[3] is None:
                mid = (node[0] + node[1]) >> 1
                node[3] = [node[0], mid, 0, None, None, 0]
                node[4] = [mid, node[1], 0, None, None, 0]
            stack[stack_index] = (node, a, b, k, -1)
            stack_index += 1
            stack[stack_index] = (node[3], a, b, k, node[2])
            stack_index += 1
            stack[stack_index] = (node[4], a, b, k, node[2])
            stack_index += 1
print(tree[5])