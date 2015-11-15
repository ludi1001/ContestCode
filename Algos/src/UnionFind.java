import java.util.*;
public class UnionFind {
	public static void union(int[]parent, int[]rank, int x, int y) {
		int px = findPathCompression(parent, rank, x);
		int py = findPathCompression(parent, rank, y);
		if(px == py)
			return;
		
		if(rank[px] > rank[py])
			parent[py] = px;
		else if(rank[px] < rank[py]) 
			parent[px] = py;
		else {
			parent[py] = px;
			++rank[px];
		}
	}
	public static int find(int[]parent, int[]rank, int node) {
		if(parent[node] != node)
			parent[node] = find(parent, rank, parent[node]);
		return parent[node];
	}
	public static int findPathCompression(int[]parent, int[]rank, int node) {
		int temp = node;
		while(parent[node] != node) {
			node = parent[node];
		}
		while(node != temp) {
			int old = temp;
			temp = parent[temp];
			parent[old] = node;
		}
		return node;
	}
	public static void main(String[]args) {
		int n = 10;
		
		int[]parent = new int[n];
		int[]rank = new int[n];
		for(int i = 0; i < n; ++i)
			parent[i] = i;

	}
}
