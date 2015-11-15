def g(a,b):
    while b:
        a, b = b, a % b
    return a
T = int(input())
for test_case in range(T):
    a,b,x,y = (int(x) for x in input().split())
    if g(x,y) == g(a,b):
        print('YES')
    else:
        print('NO')