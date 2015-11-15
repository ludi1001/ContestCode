s = input()
possible = set()
for i in range(len(s)+1):
    for j in range(26):
        possible.add(s[0:i] + chr(j + ord('a')) + s[i:])
print(len(possible))