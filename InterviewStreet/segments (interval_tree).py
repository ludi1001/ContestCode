def binary_search(arr, x, index):
	low = 0
	high = len(arr)
	while low < high:
		mid = (high + low)//2
		if arr[mid][index] < x:
			low = mid + 1
		elif arr[mid][index] > x: 
			high = mid
		else:
			while mid < len(arr) and arr[mid][index] == x:
				mid += 1
			return mid
	return high
	
class interval_tree(object):
	def __init__(self, nodes):
		if len(nodes) < 1:
			self.init = False
			return
		self.init = True
		min = nodes[0][0]
		max = nodes[0][1]
		for node in nodes:
			if node[0] < min:
				min = node[0]
			elif node[1] > max:
				max = node[1]
		self.center = (min + max)//2
		self.min = min
		self.max = max
		
		center_nodes = []
		left_nodes = []
		right_nodes = []
		for node in nodes:
			if node[0] <= self.center and node[1] >= self.center:
				center_nodes.append(node)
			elif node[1] < self.center:
				left_nodes.append(node)
			else:
				right_nodes.append(node)
		from operator import itemgetter
		self.center_left = sorted(center_nodes, key=itemgetter(0))
		self.center_right = sorted(center_nodes, key=itemgetter(1))
		self.left_child = interval_tree(left_nodes)
		self.right_child = interval_tree(right_nodes)
	
	def find_intervals(self, x):
		if self.init == False:
			return []
		intervals = []
		if x < self.center:
			intervals += self.left_child.find_intervals(x)
			high = binary_search(self.center_left, x, 0)
			intervals += self.center_left[0:high]
		elif x > self.center:
			intervals += self.right_child.find_intervals(x)
			high = binary_search(self.center_right, x - 1, 1)
			intervals += self.center_right[high:]
		else:
			intervals = self.center_left
		return intervals

if __name__ == "__main__":
	N = int(raw_input())
	lines = []
	for i in range(N):
		(x,y,l) = raw_input().split()
		lines.append((int(x),int(x) + int(l), int(y)))
	tree = interval_tree(lines)
	Q = int(raw_input())
	for query in range(Q):
		(x,y,l) = raw_input().split()
		x = int(x)
		y = int(y)
		l = int(l)
		count = 0
		lines = tree.find_intervals(x)
		for line in lines:
			if x >= line[0] and x <= line[1]:
				if line[2] >= y and line[2] <= y + l:
					count += 1
		print count