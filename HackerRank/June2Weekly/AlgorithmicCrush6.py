def update_tree(node, a, b, k, k2):
    global mem, mem_index
    node[2] += k2
    node[5] += k2
    if node[0] >= a and node[1] <= b:
        node[2] += k
        node[5] += k
    elif node[1] <= a or node[0] >= b:
        return
    else:
        if node[3] == -1:
            mid = (node[0] + node[1]) >> 1
            node[3] = mem_index
            mem[mem_index][0] = node[0]
            mem[mem_index][1] = mid
            mem_index += 1
            node[4] = mem_index
            mem[mem_index][0] = mid
            mem[mem_index][1] = node[1]
            mem_index += 1
        update_tree(mem[node[3]], a, b, k, node[2])
        update_tree(mem[node[4]], a, b, k, node[2])
        node[2] = 0
        node[5] = mem[node[3]][5] if mem[node[3]][5] > mem[node[4]][5] else mem[node[4]][5]
N,M = (int(x) for x in raw_input().split())
mem = [[0,0,0,-1,-1,0] for i in xrange(N)]
mem_index = 1
tree = mem[0]
tree[1] = N
for i in xrange(M):
    a,b,k = (int(x) for x in raw_input().split())
    a -= 1
    b -= 1
    update_tree(tree, a, b + 1, k, 0)
print(tree[5])