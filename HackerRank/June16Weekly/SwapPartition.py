N, K = (int(x) for x in input().split())
s = set()
s.add(tuple(range(N)))
for i in range(K):
    temp_set = set()
    for el in s:
        for j in range(N):
            for k in range(j+1,N):
                temp = list(el)
                temp[k], temp[j] = temp[j], temp[k]
                temp_set.add(tuple(temp))
    s |= temp_set
for e in sorted(list(s)):
    print(e)
print(len(s))