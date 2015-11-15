import java.util.*;
public class G {
	public static void main(String[]args) throws Exception {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		boolean[][] g = new boolean[N][N];
		int[]color = new int[N];
		while(in.hasNext()) {
			String[]tokens = in.nextLine().split("-");
			int u = Integer.parseInt(tokens[0])-1;
			int v = Integer.parseInt(tokens[1])-1;
			if(u == -1) break;
			g[u][v] = true;
			g[v][u] = true;
		}
		
		if(!color(g, 0, color)) throw new Exception();
		for(int i = 0; i < N; ++i) {
			System.out.println((i+1) + " " + color[i]);
		}
	}
	public static boolean color(boolean[][]g, int node, int[]color) {
		if(color[node] != 0) {
			for(int i = 0; i < g.length; ++i) {
				if(g[node][i]) {
					if(color[i] == color[node]) return false;
				}
			}
			return true;
		}
		
		for(int c = 1; c <= 4; ++c) {
			color[node] = c;
			outer:
			while(true) {
				for(int i = 0; i < g.length; ++i) {
					if(!g[node][i]) continue;
					if(!color(g, i, color)) break outer;
				}
				return true;
			}
		}
		color[node] = 0;
		return false;
	}
}
