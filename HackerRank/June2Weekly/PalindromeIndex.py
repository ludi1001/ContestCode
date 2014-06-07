def get_index(str, check=True):
    start = 0
    end = len(str) - 1
    while start <= end:
        if str[start] == str[end]:
            start += 1
            end -= 1
        elif check:
            if get_index(str[:start] + str[(start + 1):], False) == -1:
                return start
            else:
                return end
        else:
            return 1
    return -1
    
    
T = int(raw_input())
for i in range(T):
    print(get_index(raw_input()))