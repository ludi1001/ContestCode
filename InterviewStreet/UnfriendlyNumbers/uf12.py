import math
def sieve(n):
	max = int(math.sqrt(n)) + 1
	bitset = (max+2)*[True]
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
	
def factorize(n):
	prime_factors, exp_count = prime_factorize(n)
	from itertools import product
	exp_comb = product(*(range(i + 1) for i in exp_count))
	factors = []
	for e in exp_comb:
		f = 1
		for pair in zip(prime_factors, e):
			f *= pair[0]**pair[1]
		factors.append(f)
	return factors

def gcd(num):
	a = K
	b = num
	while b != 0:
		c = b
		b = a % b
		a = c
	return a

	
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
numbers = (int(x) for x in raw_input().split())
s = set()
for num in numbers:
	g = gcd(num)
	s.add(g)
for num in s:
	i = 0
	while i < len(factors):
		if num % factors[i] == 0:
			del factors[i]
		else:
			i += 1
print len(factors)
