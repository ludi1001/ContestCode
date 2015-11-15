class Node:
    __slots__ = ['start','end','max_val','k','left','right']
    def __init__(self, start, end):
        self.start = start
        self.end = end
        self.k = 0
        self.max_val = 0
        self.left = None
        self.right = None
    
    def update(self, a, b, k, k2):
        self.k += k2
        self.max_val += k2
        
        if self.start >= a and self.end <= b:
            self.k += k
            self.max_val += k
        elif self.end <= a or self.start >= b:
            return
        else:
            if self.left is None:
                mid = (self.start + self.end) >> 1
                self.left = Node(self.start, mid)
                self.right = Node(mid, self.end)
            self.left.update(a, b, k, self.k)
            self.right.update(a, b, k, self.k)
            self.k = 0
            self.max_val = self.left.max_val if self.left.max_val > self.right.max_val else self.right.max_val
N,M = (int(x) for x in raw_input().split())
tree = Node(0, N)
for i in xrange(M):
    a,b,k = (int(x) for x in raw_input().split())
    a -= 1
    b -= 1
    tree.update(a, b + 1, k, 0)
print(tree.max_val)