import java.util.Arrays;
import java.util.Scanner;
public class J {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()) {
			long h = in.nextInt();
			long b = in.nextInt();
			long t = in.nextInt();
			long[]w = new long[3];
			for(int i = 0; i < w.length; ++i) w[i] = in.nextInt();
			long[]dp = new long[(int) h+1];
			Arrays.fill(dp, -1);
			dp[0] = 0;
			for(int i = 1; i <= h; ++i) {
				long width = t + (b-t)*(h-i)/h;
				for(long s : w) {
					if(i-s < 0 || dp[(int) (i-s)] < 0) continue;
					dp[i] = Math.max(dp[i], dp[(int) (i-s)] + (width/s)*(width/s)*s*s*s);
				}
			}
			long res = 0;
			for(int i = 0; i < dp.length; ++i)
				res = Math.max(res, dp[i]);
			System.out.println(res);
		}
	}

}
