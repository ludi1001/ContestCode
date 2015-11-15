import java.util.*;
public class G {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long[][]choose = new long[55][55];
		choose[0][0] = 1;
		for(int i = 1; i < 55; ++i) {
			choose[i][0] = 1;
			for(int j = 1; j <= i; ++j)
				choose[i][j] = choose[i-1][j-1] + choose[i-1][j];
		}
		while(in.hasNextLong()) {
			long N = in.nextLong();
			long levels = -1 + (Long.bitCount(N) % 3 == 0 ? 1 : 0);
			long x = N;
			int mod = 0;
			while(x > 0) {
				int bits = Long.toBinaryString(x).length();
				int i = mod;
				while(i <= bits-1) {
					levels += choose[bits-1][i];
					i += 3;
				}
				x &= ~(1L << (bits-1));
				--mod;
				if(mod < 0) mod = 2;
			}
			System.out.println("Day " + N + ": Level = " + levels);
		}
	}

}
