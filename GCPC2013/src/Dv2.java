import java.util.*;
public class Dv2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			int n = in.nextInt() + 2;
			int[][]coords = new int[n][2];
			for(int i = 0; i < n; ++i) {
				coords[i][0] = in.nextInt();
				coords[i][1] = in.nextInt();
			}
			boolean[][]adj = new boolean[n][n];
			for(int i = 0; i < n; ++i)
				for(int j = i; j < n; ++j) {
					int dx = coords[i][0] - coords[j][0];
					int dy = coords[i][1] - coords[j][1];
					int dist = Math.abs(dx) + Math.abs(dy);
					if(dist <= 20 * 50) {
						adj[i][j] = true;
						adj[j][i] = true;
					}
				}
			
			Set<Integer> visited = new HashSet<Integer>();
			Queue<Integer> qu = new LinkedList<Integer>();
			visited.add(0);
			qu.add(0);
			boolean found = false;
			while(!qu.isEmpty()) {
				int node = qu.poll();
				if(node == n - 1) {
					found = true;
					break;
				}
				for(int i = 0; i < n; ++i) {
					if(adj[node][i]) {
						if(!visited.contains(i)) {
							visited.add(i);
							qu.add(i);
						}
					}
				}
			}
			if(found) System.out.println("happy");
			else System.out.println("sad");
		}
	}

}
