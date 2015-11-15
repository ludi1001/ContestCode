import java.util.*;
public class Problem7 {
	public static int doSum(int x, int a) {
		int s = a*(2*x-1) + a*(a-1);
		/*for(int i = 0; i < a; ++i) {
			s += 2*x - 1 + 2*i;
		}*/
		return s;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		while(k-->0) {
			int n = in.nextInt();
			int count = 0;
			HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
			/*for(int x = 1; x <= n; ++x) {
				for(int y = x+1; y <= n; ++y) {
					for(int a = 1; a <= n-x; ++a) {
						for(int b = 1; b <= n-y; ++b) {
							
							if(doSum(x,a) == doSum(y,b)) {
								//printSolution(a,b,x-1,y-1);
								addSolution(map, doSum(x,a));
								++count;
							}
						}
					}
				}
			}*/
			for(int x = 1; x <= n; ++x) {
				for(int a = 1; a <= n-x+1; ++a) {
					addSolution(map, doSum(x,a));
				}
			}
			
			System.out.println(getDuplicates(map));
			map.clear();
			int count2 = 0;
			for(int a = 1; a <= n; ++a) {
				for(int b = 1; b <= n; ++b) {
					// x + (x + 2) + (x + 4) + ... + (x + 2(a-1)) = y + (y + 2) + ... +  (y + 2(b-1))
					// solve ax - by = (b*b - a*a) / 2 
					int p = b*b - a*a;
					if(p % 2 != 0) continue;
					p /= 2;
					int g = gcd(a,b);
					if(p % g != 0) continue;
					int[]sol = extended_euclidean(a/g, b/g);
					int factor = p/g;
					int x = sol[0] * factor;
					int y = -sol[1] * factor;
					//if(a*x-b*y != (b*b-a*a)/2) continue; //bezouts not returning proper solution
					int na = a/g;
					int nb = b/g;
					if(x > 0) {
						int m = x/nb;
						x -= m*nb;
						y -= m*na;
					}
					if(y > 0) {
						int m = y/na;
						x -= m*nb;
						y -= m*na;
					}
					if(x < 0) {
						int m = -x/nb;
						if(x % nb != 0) ++m;
						x += m*nb;
						y += m*na;
					}
					if(y < 0) {
						int m = -y/na;
						if(y % na != 0) ++m;
						x += m*nb;
						y += m*na;
					}
					if((x+a) > n || (y+b) > n) continue; //no valid solution
					//display solution
					if(a == b && x == y) continue; //idempotent solution
					int mx = (n-a-x)/nb;
					int my = (n-b-y)/na;
					count2 += 1 + Math.min(mx, my);
					int t = 0;
					while((x+a) <= n && (y+b) <= n) {
						//printSolution(a,b,x,y);
						addSolution(map, doSum(x+1,a));
						x += nb;
						y += na;
						++t;
					}
				}
			}
			System.out.println(getDuplicates(map));
			System.out.println(count + " " + count2/2);
		}
	}
	static void addSolution(Map<Integer,Integer> map, int s) {
		if(!map.containsKey(s))
			map.put(s, 0);
		map.put(s, map.get(s) + 1);
	}
	static int getDuplicates(Map<Integer, Integer> map) {
		int count = 0;
		for(int v : map.values())
			count += v > 1 ? 1 : 0;
		return count;
	}
	static void printSolution(int a, int b, int x, int y) {
		System.out.print(2*x+1);
		for(int i = 1; i < a; ++i) {
			System.out.print(" + " + (2*x+1+2*i));
		}
		System.out.print(" = " + (2*y+1));
		for(int j = 1; j < b; ++j) {
			System.out.print(" + " + (2*y+1+2*j));
		}
		System.out.println();
	}
	static int gcd(int a, int b) {
	          while(b != 0) {
	                  int t = a%b;
	                  a = b;
	                  b = t;
	          }
	          return a;
	  }
	  static int[] extended_euclidean(int a, int b) {
	          int x = 0, lx = 1, y = 1, ly = 0;
	          while(b != 0)
	          {
	                  int t = a%b, q = a/b;
	                  a = b; b = t;
	                  t = x; x = lx-q*x; lx = t;
	                  t = y; y = ly-q*y; ly = t;
	          }
	          return new int[]{ lx, ly };
	  }
}
