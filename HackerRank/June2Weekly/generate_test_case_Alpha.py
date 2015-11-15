import random
T = 10
print(T)
for t in range(T):
    N = 150
    alpha = random.randint(10**7,10**9)
    beta = random.randint(10**7,10**9)
    arr = [random.randint(1,10**9) for i in range(N)]
    
    print(N)
    print('{0} {1}'.format(alpha, beta))
    print(' '.join([str(x) for x in arr]))