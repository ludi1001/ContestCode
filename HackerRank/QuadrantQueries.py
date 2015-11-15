def get_val():
    x,y = (int(x) for x in input().split())
    value = 0
    if x < 0:
        value += 2
    if y < 0:
        value += 1
    return value
def construct_tree(start, end):
    if start == end:
        return None
    if start + 1 == end:
        return [start, end, get_val(), None, None]
    mid = (start + end)//2
    return [start, end, 0, construct_tree(start, mid), construct_tree(mid, end)]
def update_tree(node, a, b, k):
    if node is None:
        return
    if node[0] >= a and node[1] <= b:
        node[2] ^= k
    elif node[1] <= a or node[0] >= b:
        return
    else:
        update_tree(node[3], a, b, k)
        update_tree(node[4], a, b, k)
def count(node, a, b, k, store):
    if node is None:
        return
    if node[1] <= a or node[0] >= b:
        return
    if node[3] is None and node[4] is None:
        store[node[2] ^ k] += 1
    else:
        count(node[3], a, b, node[2] ^ k, store)
        count(node[4], a, b, node[2] ^ k, store)
N = int(input())
tree = construct_tree(0, N)
Q = int(input())
for i in range(Q):
    q,a,b = input().split()
    a, b = int(a) - 1, int(b)
    if q == 'X':
        update_tree(tree, a, b, 1)
    elif q == 'Y':
        update_tree(tree, a, b, 2)
    else:
        store = [0, 0, 0, 0]
        count(tree, a, b, 0, store)
        print(str(store[0]) + ' ' + str(store[2]) + ' ' + str(store[3]) + ' ' + str(store[1]))