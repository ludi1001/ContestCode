with open('test.txt', 'w') as f:
    f.write('100000\n')
    for i in range(99999):
        f.write('{0} {1} r\n'.format(i+1,i+2))