n = int(input())
grid = [[0]*n for i in range(n)]
for r in range(n):
    s = input()
    for c in range(n):
        grid[r][c] = ord(s[c]) - ord('0')
max_clean_count = 0
for r in range(n):
    state = [x ^ 1 for x in grid[r]]
    clean_count = 0
    for r2 in range(n):
        if all(grid[r2][c] ^ state[c] == 1 for c in range(n)):
            clean_count += 1
    max_clean_count = max(max_clean_count, clean_count)  
print(max_clean_count)    