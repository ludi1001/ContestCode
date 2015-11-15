import java.util.*;
public class A {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		
		List<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		//sieve
		/*
		for(int i = 3; i < 1000000000; i += 2) {
			boolean works = true;
			for(int p : primes)
				if(i % p == 0) {
					works = false;
					break;
				}
			
			if(works)
				primes.add(i);
		}
		System.out.println(primes);*/
		System.out.println(new A().sievePrimesUnder(100));
		BitSet marked = new BitSet(1000000000);
		marked.set(0); marked.set(1);
		primes.add(2);
		int candidate = 2;
		while(candidate < marked.size()) {
			for(int j = candidate; j < marked.size(); j += candidate)
				marked.set(j);
			while(candidate < marked.size() && marked.get(candidate))
				++candidate;
			primes.add(candidate);
		}
		
		int testCases = in.nextInt();
		while(testCases-- > 0) {
			int n = in.nextInt();
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			while(n > 1) {
				//factor n
				int temp = n;
				for(int p : primes) {
					if(p > temp)
						break;
					while(temp % p == 0) {
						if(!map.containsKey(p))
							map.put(p,  0);
						map.put(p, map.get(p) + 1);
						temp /= p;
					}
				}
				--n;
			}
			int k = in.nextInt();
			Map<Integer, Integer> kmap = new HashMap<Integer, Integer>();
			for(int p : primes) {
				if(p > k)
					break;
				while(k % p == 0) {
					if(!kmap.containsKey(p))
						kmap.put(p, 0);
					kmap.put(p,  kmap.get(p) + 1);
					k /= p;
				}
			}
			int i = Integer.MAX_VALUE;
			for(int p : kmap.keySet()) {
				if(!map.containsKey(p)) {
					i = 0;
					break;
				}
				i = Math.min(i, map.get(p)/kmap.get(p));
			}
			System.out.println(i);
		}
	}
	
	public List<Integer> sievePrimesUnder(int n) {
		List<Integer> primes = new ArrayList<Integer>();
		BitSet marked = new BitSet(n+1);
		marked.set(0); marked.set(1);
		primes.add(2);
		int candidate = 2;
		while(candidate < n) {
			for(int j = candidate; j < n; j += candidate)
				marked.set(j);
			while(candidate < n && marked.get(candidate))
				++candidate;
			if(candidate < n)
				primes.add(candidate);
		}
		return primes;
	}
}
