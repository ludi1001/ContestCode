T = int(input())
for test_case in range(T):
    R1, R2 = (int(n) for n in input().split())
    x1 = [int(n) for n in input().split()]
    a1 = [int(n) for n in input().split()]
    x2 = [int(n) for n in input().split()]
    a2 = [int(n) for n in input().split()]
    
    dx = [0]*3
    da = [0]*3
    da_len2 = 0
    dot = 0
    for i in range(3):
        dx[i] = x1[i] - x2[i]
        da[i] = a1[i] - a2[i]
        da_len2 += da[i]*da[i]
        dot += dx[i]*da[i]  
    if dot >= 0:
        print('NO')
        continue
    s = 0
    for i in range(3):
        a = dx[i]*da_len2 - da[i]*dot
        s += a*a
    if s <= da_len2*da_len2*(R1 + R2)*(R1 + R2):
        print('YES')
    else:
        print('NO')