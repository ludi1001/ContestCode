T = 1#int(input())
for test_case in range(T):
    i = int(input())
    for k in range(1,i+1):
        str = '{0:02d}: '.format(k)
        c = '.'
        count = k - 1
        if count == 0:
            count = k
            c = 'x'
        for j in range(1,i+1):
            str += c
            count -= 1
            if count == 0:
                count = k
                c = '.' if c == 'x' else 'x'
        print(str)