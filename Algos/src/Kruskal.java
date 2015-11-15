import java.util.*;
public class Kruskal {
	private static class Edge implements Comparable<Edge>{
		public int i;
		public int j;
		public int c;
		public Edge(int i, int j, int c) {
			if(i > j) {
				int temp = i;
				i = j;
				j = temp;
			}
			this.i = i;
			this.j = j;
			this.c = c;
		}
		@Override
		public int compareTo(Edge e) {
			int diff = this.c - e.c;
			if(diff != 0)
				return diff;
			diff = this.i - e.i;
			if(diff != 0)
				return diff;
			return this.j - e.j;
		}
	}
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		TreeSet<Edge> set = new TreeSet<Edge>();
		int[]update = new int[2001];
		int[]parent = new int[2001];
		while(testCases > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			set.clear();
			for(int i = 0; i < m; ++i)
				set.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
			if(k == 0) {
				System.out.println("Impossible!");
				--testCases;
				continue;
			}
			else if(k >= n) {
				System.out.println(0);
				--testCases;
				continue;
			}
			int trees = n;
			int cost = 0;
			for(int i = 1; i < n + 1; ++i)
				parent[i] = i;
			while(!set.isEmpty()) {
				Edge e = set.pollFirst();
				int p = e.i;
				int ui = 0;
				while(p != parent[p]) {
					update[ui++] = p;
					p = parent[p];
				}
				int p2 = e.j;
				while(p2 != parent[p2]) {
					update[ui++] = p2;
					p2 = parent[p2];
				}
				for(int i = 0; i < ui; ++i)
					parent[update[i]] = p;
				if(p != p2) {
					parent[p2] = p;
					cost += e.c;
					--trees;
					if(trees == k)
						break;
				}
			}
			if(trees == k)
				System.out.println(cost);
			else
				System.out.println("Impossible!");
			--testCases;
		}
	}
}