store = {}
MOD = 10**9 + 9
def do(arr, start, end):
    global store
    global MOD
    if start == end:
        return {(0,0): 1}
    if (start, end) in store:
        return store[(start, end)]
    r = {}
    for i in range(start, end):
        if i + 1 != end and arr[i] == arr[i+1]:
            continue
        left = do(arr, start, i)
        right = do(arr, i + 1, end)
        for item_l,left_count in left.items():
            for item_r,right_count in right.items():
                key = ((item_l[1] + item_r[1] + arr[i]) % MOD, (item_l[0] + item_r[0]) % MOD)
                if key not in r:
                    r[key] = 0
                r[key] += left_count * right_count
    store[(start, end)] = r
    return r  
    
T = int(input())
for test_case in range(T):
    N = int(input())
    alpha, beta = (int(x) for x in input().split())
    arr = [int(x) for x in input().split()]
    arr = sorted(arr)
    r = do(arr, 0, N)
    s = 0
    for item, count in r.items():
        s += (alpha*item[0] - beta*item[1])*count
        s %= MOD
    print(s)