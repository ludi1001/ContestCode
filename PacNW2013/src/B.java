import java.util.*;
public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		long[][]g = new long[100][100];
		while(T-- > 0) {
			int N = in.nextInt();
			int K = in.nextInt();
			int M = in.nextInt();
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					g[i][j] = 1L << 60;
			for(int i = 0; i < N; ++i)
				g[i][i] = 0;
			while(M-- > 0) {
				int u = in.nextInt();
				int v = in.nextInt();
				int d = in.nextInt();
				g[u][v] = g[v][u] = d;
			}
			for(int k = 0; k < N; ++k)
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						if(g[i][j] > g[i][k] + g[k][j])
							g[i][j] = g[i][k] + g[k][j];
			long lo = 0, hi = 0;
			for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j)
				if(g[i][j] > hi) hi = g[i][j];
			long[][]g2 = new long[N][N];
			boolean works = false;
			while(lo < hi) {
				long range = lo + (hi - lo)/2;
				
				works = true;
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						g2[i][j] = g[i][j] <= range ? 1 : (1L << 30);
				for(int i = 0; i < N; ++i)
					g2[i][i] = 0;
				for(int k = 0; k < N; ++k)
					for(int i = 0; i < N; ++i)
						for(int j = 0; j < N; ++j)
							if(g2[i][j] > g2[i][k] + g2[k][j])
								g2[i][j] = g2[i][k] + g2[k][j];
				outer:
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						if(g2[i][j] > K) {
							works = false;
							break outer;
						}
				
				if(works)
					hi = range;
				else
					lo = range + 1;
			}
			System.out.println(lo);
		}
	}
}
