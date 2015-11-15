for T in range(int(input())):
    a, b, x = (int(z) for z in input().split())
    if b < 0 and a != 1:
        print(0)
        continue
    elif a == 1 and b < 0:
        n = 1
    else:
        n = a**b
    lo = x*(n//x)
    hi = lo + x
    if n - lo <= hi - n:
        print(lo)
    else:
        print(hi)