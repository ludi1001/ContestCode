n = int(input())
counts = {}
for i in range(n):
    s = input()
    if s not in counts:
        counts[s] = 0
    counts[s] += 1
print(max(counts.values()))