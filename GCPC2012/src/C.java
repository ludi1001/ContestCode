import java.util.*;
public class C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int testCase = 0; testCase < t; ++testCase) {
			long mod = in.nextLong();
			long a = in.nextLong();
			long g = gcd(a, mod);
			if(g > 1)
				System.out.println("IMPOSSIBLE");
			else {
				long[]ans = bezoutIdentitySolve(a, mod);
				while(ans[0] < 0 || ans[1] > 0) {
					ans[0] += mod;
					ans[1] -= a;
				}
				System.out.println(ans[0]);
			}
		}
	}
	public static long gcd(long a, long b) {
		while(b != 0) {
			long t = a%b;
			a = b;
			b = t;
		}
		return a;
	}
	
	public static long inv(long mod, long a) {
		if (a%mod == 1)
			return 1;
		long u = 1+mod/a;
		long a1 = (a*u)%mod;
		long u1 = inv(mod, a1);
		return (u*u1)%mod;
	}
	public static long[] bezoutIdentitySolve( long a, long b ){
		long x = 0, lx = 1, y = 1, ly = 0;
		long bo = b;
		while( b != 0 ){
			long t = a%b, q = a/b;
			a = b; b = t;
			t = x; x = lx-q*x; lx = t;
			t = y; y = ly-q*y; ly = t;
		}
		return new long[]{lx, ly};
	}
}
