  N = int(input())
A = [int(x) for x in input().split()]
for test_case in range(int(input())):
    x, y = (int(z)-1 for z in input().split())
    even = True
    if x > y or (A[x] % 2 == 1):
        even = False
    if x < N - 1 and x < y and A[x+1] == 0:
        even = False
    if even:
        print('Even')
    else:
        print('Odd')
    if even == (find(x,y) % 2 == 0):
        print('Good')
    else:
        print('Bad')
        
