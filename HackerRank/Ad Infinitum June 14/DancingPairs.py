T = int(input())
for test_case in range(T):
    i = int(input())
    s = 0
    z = i
    
    for j in range(1,i,2):
        diff = (i // j) - (i // (j+1))
        if i//j <= j:
            z = i // j
            break
        s += diff
    
    for j in range(1,z+1):
        if i % (2*j) >= j:
            s += 1
    t = 0
    """
    for j in range(1,i+1):
        if i % (2*j) >= j:
            t += 1
        else:
            t += 0
    print(s == t)
    """
    if s % 2 == 0:
        print('even')
    else:
        print('odd')
    