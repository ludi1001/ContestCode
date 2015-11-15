import java.util.*;
public class G {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int counter = 1;
		while(true) {
			int n = in.nextInt();
			int k = in.nextInt();
			if(n == 0 && k == 0)
				break;
			int[]dp1 = new int[k + 1];
			int[]dp2 = new int[k + 1];
			int[]dp = dp1;
			int[]dp_prev = dp2;
			for(int i = 0 ; i < n; ++i) {
				int e = in.nextInt();
				int d = in.nextInt();
				Arrays.fill(dp, -1);
				for(int j = 0; j <= k; ++j) {
					if(j - e >= 0 && dp_prev[j-e] != -1) {
						dp[j] = dp_prev[j - e];
					}
					if(2*j - d >= 0 && 2*j - d <= k && dp_prev[2*j-d] != -1 && dp_prev[2*j - d] + 1 > dp[j]) {
						dp[j] = dp_prev[2*j - d] + 1;
					}
				}
				if(dp == dp1) {
					dp = dp2;
					dp_prev = dp1;
				}
				else {
					dp = dp1;
					dp_prev = dp2;
				}
			}
			System.out.print(counter + ": ");
			if(dp_prev[k] == -1)
				System.out.println("Mission Impossible");
			else
				System.out.println(dp_prev[k]);
			++counter;
		}
	}

}
