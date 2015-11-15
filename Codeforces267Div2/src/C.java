import java.util.*;
public class C {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		long[]arr = new long[n];
		for(int i = 0; i < n; ++i)
			arr[i] = in.nextInt();
		long[]sum = new long[n];
		long r = 0;
		for(int i = 0; i < m; ++i)
			r += arr[i];
		sum[m-1] = r;
		for(int i = m; i < n; ++i) {
			r = r - arr[i-m] + arr[i];
			sum[i] = r;
		}
		/*long[][] dp = new long[n+1][k+1];
		for(int i = 1; i <= n; ++i) {
			dp[i][0] = 0;
			for(int j = 1; j <= k; ++j) {
				dp[i][j] = dp[i-1][j];
				if(i >= m)
					dp[i][j] = Math.max(dp[i][j], dp[i-m][j-1] + sum[i-1]);
			}
		}*/
		long[][] dp = new long[m+1][k+1];
		for(int i = 1; i <= n; ++i) {
			dp[m][0] = 0;
			for(int j = 1; j <=k ; ++j) {
				dp[m][j] = Math.max(dp[m-1][j], dp[0][j-1] + sum[i-1]);
			}
			for(int j = 0; j < m; ++j) {
				for(int p = 0; p <= k; ++p)
					dp[j][p] = dp[j+1][p];
			}
		}
		System.out.println(dp[m][k]);
	}

}
