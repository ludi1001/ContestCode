"""
The median of M numbers is defined as the middle number after sorting them in order, if M is odd or the average number of the middle 2 numbers (again after sorting) if M is even. You have an empty number list at first. Then you can add or remove some number from the list. For each add or remove operation, output the median of numbers in the list.
 
Example : For a set of m = 5 numbers, { 9, 2, 8, 4, 1 } the median is the third number in sorted set { 1, 2, 4, 8, 9 } which is 4. Similarly for set of m = 4, { 5, 2, 10, 4 }, the median is the average of second and the third element in the sorted set { 2, 4, 5, 10 } which is (4+5)/2 = 4.5  
 
Input:
 
The first line is an integer n indicates the number of operations. Each of the next n lines is either "a x" or "r x" which indicates the operation is add or remove.
 
Output:
 
For each operation: If the operation is add output the median after adding x in a single line. If the operation is remove and the number x is not in the list, output "Wrong!" in a single line. If the operation is remove and the number x is in the list, output the median after deleting x in a single line. (if the result is an integer DO NOT output decimal point. And if the result is a double number , DO NOT output trailing 0s.)
 
Constraints:
 
0 < n <= 100,000
 
for each "a x" or "r x" , x will fit in 32-bit integer.
 
Sample Input:
 
7
r 1
a 1
a 2
a 1
r 1
r 2
r 1
 
Sample Output:
Wrong!
1
1.5
1
1.5
1
Wrong!
 
Note: As evident from the last line of the input, if after remove operation the list becomes empty you have to print "Wrong!" ( quotes are for clarity ). 
"""
import heapq
class Heap(object):
	def __init__(self):
		self.heap = []
		self.len = 0
		self.map = {}

	def pop(self):
		last = heapq.heappop(self.heap)
		self.map[last] -= 1
		self.len -=1
		if self.len != 0:
			while not (self.heap[0] in self.map) or self.map[self.heap[0]] == 0:
				heapq.heappop(self.heap)
		return last
		
	def push(self, val):
		heapq.heappush(self.heap, val)
		if val in self.map:
			self.map[val] += 1
		else:
			self.map[val] = 1
		self.len += 1
		
	def remove(self, val):	
		if val == self.heap[0]:
			self.pop()
		else:
			self.len -=1 
			self.map[val] -= 1	
	
	def peek(self):
		return self.heap[0]
	
	def has(self, val):
		return val in self.map and self.map[val] > 0
	
N = int(raw_input())

smallheap = Heap()
bigheap = Heap()
median = None
for i in range(0, N):
	tmp = raw_input().split(" ")
	c = tmp[0]
	val = int(tmp[1])
	if c == 'a':
		if median == None:
			bigheap.push(-val)
			median = val
			print val
			continue
		if bigheap.len == smallheap.len:
			if val <= median:
				bigheap.push(-val)
			else:
				smallheap.push(val)
				bigheap.push(-smallheap.pop())
			median = -bigheap.peek()
		else:
			if val <= median:
				bigheap.push(-val)
				smallheap.push(-bigheap.pop())
			else:
				smallheap.push(val)
			median = float(smallheap.peek()-bigheap.peek())/2
			if float(median) == int(median):
				median = int(median)
		print median
	elif c == 'r':
		if bigheap.has(-val) or smallheap.has(val):
			if bigheap.len == smallheap.len:
				if val <= median:
					bigheap.remove(-val)
					bigheap.push(-smallheap.pop())
				else:
					smallheap.remove(val)
				median = -bigheap.peek()
			else:
				if val <= median:
					bigheap.remove(-val)
				else:
					smallheap.remove(val)
					smallheap.push(-bigheap.pop())
				if bigheap.len == 0:
					print 'Wrong!'
					median = None
					continue
				median = float(smallheap.peek()-bigheap.peek())/2
				if float(median) == int(median):
					median = int(median)
			print median
		else:
			print 'Wrong!'