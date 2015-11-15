"""
There is one friendly number and N unfriendly numbers. We want to find how many numbers are there which exactly divide the friendly number, but does not divide any of the unfriendly numbers.

Input Format:
The first line of input contains two numbers N and K seperated by spaces. N is the number of unfriendly numbers, K is the friendly number.
The second line of input contains N space separated unfriendly numbers.
 
Output Format:
Output the answer in a single line.
 
Constraints:
1 <= N <= 10^6
1 <= K <= 10^13
1 <= unfriendly numbers <= 10^18
 
Sample Input:
8 16
2 5 7 4 3 8 3 18
 
Sample Output:
1
 
Explanation :
Divisors of the given friendly number 16, are { 1, 2, 4, 8, 16 } and the unfriendly numbers are {2, 5, 7, 4, 3, 8, 3, 18}. Now 1 divides all unfriendly numbers, 2 divide 2, 4 divide 4, 8 divide 8 but 16 divides none of them. So only one number exists which divide the friendly number but does not divide any of the unfriendly numbers. So the answer is 1.   
"""
import math
def sieve(n):
	max = int(math.sqrt(n)) + 1
	bitset = (max+2)*[True]
	#primes = []
	for i in xrange(2, max + 1):
		if bitset[i]:
			yield i
			for j in xrange(i + i, max + 1, i):
				bitset[j] = False

def prime_factorize(n):
	prime_factor = []
	exp_count = []
	for p in sieve(n):
		if n % p == 0:
			count = 0
			while n % p == 0:
				count += 1
				n /= p
			prime_factor.append(p)
			exp_count.append(count)
			if n == 1:
				break
	if n != 1:
		prime_factor.append(n)
		exp_count.append(1)
	return (prime_factor, exp_count)
	
import copy
class Tree(object):
	def __init__(self, exp_count, depth):
		index = depth % len(exp_count)
		self.depth = index
		self.exp_count = exp_count
		self.marked = 0
		self.left = None
		
		if exp_count[index][0] != exp_count[index][1]:
			self.divide = (exp_count[index][1] + exp_count[index][0])/2
			left_count = copy.deepcopy(exp_count)
			left_count[index][1] = self.divide
			self.left = Tree(left_count, depth + 1)
			right_count = copy.deepcopy(exp_count)
			right_count[index][0] = self.divide + 1
			self.right = Tree(right_count, depth + 1)
		else:
			index2 = index + 1
			if index2 >= len(exp_count):
				index2 = 0
			while index2 != index and exp_count[index2][0] == exp_count[index2][1]:
				index2 += 1
				if index2 >= len(exp_count):
					index2 = 0
			if exp_count[index2][0] != exp_count[index2][1]:
				self.depth = index2
				self.divide = (exp_count[index2][1] + exp_count[index2][0])/2
				left_count = copy.deepcopy(exp_count)
				right_count = copy.deepcopy(exp_count)
				left_count[index2][1] = self.divide
				right_count[index2][0] = self.divide + 1
				self.left = Tree(left_count, self.depth + 1)
				self.right = Tree(right_count, self.depth + 1)
	
	def mark(self, counts, check_containment=True):
		if self.marked != 0:
			return
		elif self.left == None:
			self.marked = 1
		else:
			if check_containment and self.is_contained(counts):
				total = 1
				for e in self.exp_count:
					total *= e[1] - e[0] + 1
				self.marked = total
			else:
				self.left.mark(counts, True)
				if counts[self.depth] > self.divide:
					self.right.mark(counts, False)
					
	def is_contained(self, counts):
		if self.exp_count[self.depth][1] > counts[self.depth]:
			return False
		for i in xrange(len(counts)):
			if self.exp_count[i][1] > counts[i]:
				return False
		return True
		
	def count(self):
		if self.marked != 0 or self.left == None:
			return self.marked
		else:
			marked = self.left.count()
			if self.right != None:
				marked += self.right.count()
			return marked
			
def construct_tree(exp_count):
	arr = []
	for e in exp_count:
		arr.append([0, e])
	tr = Tree(arr, 0)
	return tr

def factor(num, exp_count_num):
	for i in prange:
		count = 0
		while num % primes[i] == 0 and count < exp_count_prime[i]:
			count += 1
			num /= primes[i]
		exp_count_num[i] = count

def gcd(num):
	a = K
	b = num
	while b != 0:
		c = b
		b = a % b
		a = c
	return a

def factorize(n):
	from itertools import product
	exp_comb = product(*(range(i + 1) for i in exp_count_prime))
	factors = {}
	for e in exp_comb:
		f = 1
		for pair in zip(primes, e):
			f *= pair[0]**pair[1]
		factors[f] = e
	return factors
	
#f = open("testfile_987654.txt", "r")			
#N,K = (int(x) for x in f.readline().split())
#numbers = (int(x) for x in f.readline().split())
#f.close()
N, K = (int(x) for x in raw_input().split())
if K == 1:
	print 0
	exit()
primes, exp_count_prime = prime_factorize(K)
factors = factorize(K)
tr = construct_tree(exp_count_prime)
numbers = (int(x) for x in raw_input().split())
exp_count_num = [0]*len(primes)
prange = range(len(primes))
s = set()
for num in numbers:
	g = gcd(num)
	if g not in s:
		s.add(g)
		exp_count_num = factors[g]
		tr.mark(exp_count_num)

num_factors = 1
for e in exp_count_prime:
	num_factors *= (e + 1)
print num_factors - tr.count()
