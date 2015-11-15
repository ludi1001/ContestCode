import java.util.*;
public class K {
	public static boolean bfs(int start, int end, int[][]g, int k) {
		Queue<Integer> qu = new LinkedList<Integer>();
		HashSet<Integer> set = new HashSet<Integer>();
		qu.add(start);
		set.add(start);
		while(!qu.isEmpty()) {
			int s = qu.poll();
			if(s == end) return true;
			for(int i = 0; i < k; ++i) {
				if(g[s][i] > 0) {
					if(!set.contains(i)) {
						set.add(i);
						qu.add(i);
					}
				}
			}
			if(g[s][end] > 0)
				qu.add(end);
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[]x = new int[N];
		int[]y = new int[N];
		int[]r = new int[N];
		int[][]g = new int[N+2][N+2];
		int left = N;
		int right = N + 1;
		for(int i = 0; i < N; ++i) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
			r[i] = in.nextInt();
		}
		for(int i = 0; i < N; ++i) {
			for(int j = i + 1; j < N; ++j) {
				int dx = x[i] - x[j];
				int dy = y[i] - y[j];
				int d = r[i] + r[j];
				if (dx*dx + dy*dy < d*d) {
					g[i][j] = g[j][i] = 1;
				}
			}
			if (x[i] - r[i] <= 0) {
				g[i][left] = g[left][i] = 1;
			}
			if (x[i] + r[i] >= 200) {
				g[i][right] = g[right][i] = 1;
			}
		}
		for(int i = 1; i <= N; ++i) {
			if(bfs(left, right, g, i)) {
				System.out.println(i - 1);
				break;
			}
		}
	}

}
