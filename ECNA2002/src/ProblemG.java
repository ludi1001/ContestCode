import java.util.*;
public class ProblemG {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int c = 1;
		while(true) {
			int G = scanner.nextInt();
			int L = scanner.nextInt();
			if(G == L && L == 0)
				break;
			if(G < L)
				L = G;
			int[][]d = new int[L + 1][G + 1];
			for(int i = 0; i <= G; ++i)
				d[0][i] = i;
			for(int i = 1; i <= L; ++i) {
				for(int j = 1; j <= G; ++j) {
					d[i][j] = j;
					for(int k = 1; k <= j; ++k)
						d[i][j] += d[i - 1][j - k];
				}
			}
			System.out.println("Case " + c + ": " + d[L][G]);
			c++;
		}
	}
}
