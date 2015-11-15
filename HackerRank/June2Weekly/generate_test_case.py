import random
N = random.randint(3,1000000)#10**7)
M = random.randint(1,2*10**5)
print('{N} {M}'.format(N=N,M=M))
for i in range(M):
    a = random.randint(1,N)
    b = random.randint(a,N)
    k = random.randint(0,10**9)
    print('{0} {1} {2}'.format(a,b,k))