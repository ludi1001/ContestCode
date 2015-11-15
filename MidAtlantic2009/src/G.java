import java.util.*;
class G {

	/**
	 * @param args
	 */
	static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()) {
			int N = in.nextInt();
			if(N == 0)
				break;
			int K = in.nextInt();
			int[]count = new int[N];
			for(int i = 0; i < N; ++i)
				count[i] = in.nextInt();
			int sum = 0;
			for(int i = 0; i < N; ++i)
				sum += count[i];
			String s = "";
			int total = 0;
			for(int i = 0; i < sum; ++i) {
				int k;
				for(k = 0; k < N; ++k) {
					if(count[k] <= 0)
						continue;
					count[k] -= 1;
					int num = choose(sum - i - 1, count);
					if(total + num > K)
						break;
					total += num;
					count[k] += 1;
				}
				s += (char)(k + 'a');
			}
			System.out.println(s);
		}
		in.close();
	}
	static int choose(int n, int[]count) {
		if(n == 0)
			return 1;
		long[][]arr = new long[n + 1][n + 1];
		arr[0][0] = 1;
		for(int i = 1; i <= n; ++i) {
			arr[i][0] = 1;
			for(int j = 1; j <= n; ++j) {
				arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
			}
		}
		int prod = 1;
		int sum = 0;
		for(int c : count) {
			sum += c;
			prod *= arr[sum][c];
		}
		return prod;
	}
}
