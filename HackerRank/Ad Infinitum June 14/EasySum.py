T = int(input())
for test_case in range(T):
    N, m = (int(x) for x in input().split())
    mod_sum = m*(m-1)//2
    multiplier = N//m
    mod = N % m
    print(mod_sum * multiplier + mod*(mod+1)//2)