import java.util.*;
public class D {

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
	public static void main(String[] args) {
		ArrayList<Integer>[] map = new ArrayList[4];
		for(int i = 0; i < map.length; ++i)
			map[i] = new ArrayList<Integer>();
		for(int i = 1; i < 64; ++i)
			map[getK(i)].add(i);
		long[][]carr = choose(64, 64);
		Scanner in = new Scanner(System.in);
		while(true) {
			long low = in.nextLong();
			long high = in.nextLong();
			int x = in.nextInt();
			if(low == 0 && high == 0 && x == 0)
				break;
			if(x > 4) {
				System.out.println(0);
				continue;
			}
			if(x == 0) {
				if(low == 1) System.out.println(1);
				else System.out.println(0);
				continue;
			}
			long sum = 0;
			for(int k : map[x - 1]) sum += count(high, k, carr) - count(low, k, carr);
			if(getK(low) == x) ++sum;
			System.out.println(sum);
			//System.out.println(count(in.nextInt(), in.nextInt(), carr));
		}
	}
	public static int getK(long num) {
		int k = 0;
		while(num != 1) {
			num = Long.bitCount(num);
			++k;
		}
		return k;
	}
	public static long count(long max, int k, long[][]carr) {
		long ret = 0;
		long bit = 1L << 62;
		int shift = 63;
		while(bit != 0) {
			if((max & bit) != 0) {
				if(shift - 1 >= k)
					ret += carr[shift - 1][k];
				--k;
				if(k == 0) {
					++ret; break;
				}
				if(k < 0)
					break;
			}
			bit >>= 1;
			--shift;
		}
		return ret;
	}
}
