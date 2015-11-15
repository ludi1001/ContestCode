import java.util.Scanner;
public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[][]C = new int[11][11];
		C[0][0] = 1;
		for (int i = 1; i <= 10; ++i) {
			C[i][0] = 1;
			for (int j = 1; j <= i; ++j) {
				C[i][j] = C[i-1][j] + C[i-1][j-1];
			}
		}
		int N = in.nextInt();
		while(N-->0) {
			int R = in.nextInt();
			int S = in.nextInt();
			int X = in.nextInt();
			int Y = in.nextInt();
			int W = in.nextInt();
			long[]vals = new long[Y+1];
			long[]nvals = new long[Y+1];
			vals[0] = 1;
			nvals[0] = 1;
			for(int i = 1; i < vals.length; ++i) {
				vals[i] = vals[i-1] * (S-R+1);
				nvals[i] = nvals[i-1] * (R-1);
			}
			long s = 0;
			for(int i = 0; i <= Y; ++i) {
				if (i >= X) {
					s += vals[i]*nvals[Y-i]*C[Y][i]*(W-1);
				} else {
					s -= vals[i]*nvals[Y-i]*C[Y][i];
				}
			}
			if (s  <= 0)
				System.out.println("no");
			else
				System.out.println("yes");
		}
	}
}
