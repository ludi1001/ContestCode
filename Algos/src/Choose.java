
public class Choose {
	public static long[][] choose(int n, int k) {
		long[][]arr = new long[n + 1][k + 1];
		arr[0][0] = 1;
		int min = 0;
		for(int i = 1; i <= n; ++i) {
			arr[i][0] = 1;
			min = i;
			if(k < min)
				min = k;
			for(int j = 1; j <= k; ++j) {
				arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
			}
		}
		return arr;
	}
}
