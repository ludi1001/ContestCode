import java.util.*;
public class E {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int k = in.nextInt();
		int lo = 1, hi = b-1;
		if(a > b) {
			lo = b+1;
			hi = n;
		}
		int MOD = 1000000007;
		int[][]ways = new int[k+1][n+1];
		int[]sum = new int[n+1];
		ways[0][a] = 1;
		for(int i = 1; i <= k; ++i) {
			for(int j = 1; j <= n; ++j) 
				sum[j] = (sum[j-1] + ways[i-1][j]) % MOD;
			for(int j = lo; j <= hi; ++j) {
				int diff = (Math.abs(j-b) - 1)/2;
				int min = 0, max = n;
				if(j < b)
					max = j + diff;
				else
					min = j - diff - 1;
				ways[i][j] = (((sum[max] + MOD - sum[min]) % MOD) + (MOD - ways[i-1][j])) % MOD;
			}
			ways[i][b] = 0;
		}
		int ans = 0;
		for(int i = 1; i <= n; ++i) {
			ans += ways[k][i];
			ans %= MOD;
		}
		System.out.println(ans);
	}

}
