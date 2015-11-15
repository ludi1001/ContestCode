#solution is catalan*number of combinations
MOD = 10**9 + 9
catalan = [0]*500
cnr = [[0]*500 for i in xrange(500)]
def compute_catalan(catalan, N):
    global MOD
    catalan[0] = 1
    for i in xrange(1, N):
        for j in xrange(i):
            catalan[i] += catalan[j]*catalan[i-j-1]
        catalan[i] %= MOD
def compute_cnr(cnr, N):
    global MOD
    cnr[0][0] = 1
    for i in xrange(1,N+1):
        cnr[i][0] = 1
        for j in xrange(1,i+1):
            cnr[i][j] = ( cnr[i-1][j-1] + cnr[i-1][j] )
            cnr[i][j] %= MOD
compute_catalan(catalan,400)
compute_cnr(cnr,400)
T = int(raw_input())
for i in xrange(T):
    N = int(raw_input())
    res = 0
    for i in xrange(1,N+1):
        res += catalan[i]*cnr[N][i]
        res %= MOD
    print(res)

