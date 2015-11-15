import java.util.*;
public class B {
	static boolean unlimited = false;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> sinks = new ArrayList<Integer>();
		for(int i = 0; i < n-1; ++i) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			int k = in.nextInt();
			for(int j = 0; j < k; ++j)
				a.add(in.nextInt() - 1);
			if(a.size() == 0)
				sinks.add(i);
			g.add(a);
		}
		g.add(new ArrayList<Integer>());
		boolean pardon = false;
		boolean[]marked = new boolean[n];
		dfs(g, 0, marked);
		if(marked[n-1]) pardon = true;
		for(int s : sinks)
			if(marked[s]) pardon = false;
		
		if(pardon)
			System.out.print("PARDON ");
		else
			System.out.print("PRISON ");
		if(unlimited)
			System.out.println("UNLIMITED");
		else
			System.out.println("LIMITED");
	}
	public static void dfs(ArrayList<ArrayList<Integer>> g, int node, boolean[]marked) {
		if(marked[node]) {
			unlimited = true;
			return;
		}
		marked[node] = true;
		for(int next : g.get(node))
			dfs(g, next, marked);
	}
}
