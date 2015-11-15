MOD = 1000000007
k = int(input())
c = [int(input()) for i in range(k)]
total_c = sum(c)
comb = [[0]*(max(c)+1) for i in range(total_c+1)]
comb[0][0] = 1
for i in range(1,len(comb)):
    comb[i][0] = 1
    for j in range(1,len(comb[0])):
        comb[i][j] = comb[i-1][j-1] + comb[i-1][j]
        comb[i][j] %= MOD
w = [0]*(total_c+1)
w[0] = 1
for color in range(k):
    new_w = [0]*len(w)
    for n in range(len(new_w)):
        new_w[n] = w[n]
        for i in range(c[color]):
            if n-i-1 < 0: break
            new_w[n] += comb[n-1][i]*w[n-i-1]
            new_w[n] %= MOD
    w = new_w
print(w[total_c])