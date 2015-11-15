import java.util.*;
public class F {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int N = in.nextInt();
			int K = in.nextInt();
			int L = in.nextInt();
			if(N == 0 && K == 0 && L == 0) break;
			//Solution #1:
			/*int[][]dp = new int[N + 1][L + 1];
			for(int i = 1; i <= N; ++i) {
				int f = in.nextInt();
				int d = in.nextInt();
				for(int j = 0; j <= L; ++j) {
					if(j + d <= L)
						dp[i][j + d] = Math.max(dp[i][j + d], dp[i - 1][j] + f);
					dp[i][Math.max(0, j - K)] = Math.max(dp[i][Math.max(0, j - K)], dp[i - 1][j]);
				}
			}
			int max = 0;
			for(int i = 0; i <= L; ++i)
				if(max < dp[N][i])
					max = dp[N][i];
			System.out.println(max);*/
			//Solution #2:
			//int[][]dp = new int[N + 1][20001];
			int[][]dp2 = new int[2][20001];
			int cur = 0;
			int prev = 1;
			Arrays.fill(dp2[prev], Integer.MAX_VALUE/2);
			dp2[prev][0] = 0;
			for(int i = 1; i <= N; ++i) {
				Arrays.fill(dp2[cur], Integer.MAX_VALUE/2);
				int f = in.nextInt();
				int d = in.nextInt();
				for(int j = 0; j <= 20000; ++j) {
					dp2[cur][j] = Math.max(dp2[prev][j] - K, 0);
					if(j - f >= 0 && dp2[cur][j - f] + d <= L)
						dp2[cur][j] = Math.min(dp2[cur][j], dp2[prev][j - f] + d);
					if(dp2[cur][j] > L)
						dp2[cur][j] = Integer.MAX_VALUE/2;
				}
				if(cur == 0) {
					cur = 1; prev = 0;
				}
				else {
					cur = 0; prev = 1;
				}
			}
			int i;
			for(i = 20000; i >= 0; --i)
				if(dp2[prev][i] <= L)
					break;
			System.out.println(i);
		}
	}
}
