import java.util.*;
public class C {
	public static ArrayList<ArrayList<Integer>> scc(ArrayList<ArrayList<Integer>> g) {
		boolean[]marked = new boolean[g.size()];
		LinkedList<Integer> list = new LinkedList<Integer>();
		ArrayList<ArrayList<Integer>> comp = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < g.size(); ++i)
			dfs(g, i, marked, list);
		Arrays.fill(marked, false);
		
		for(int i = 0; i < g.size(); ++i)
			rev.add(new ArrayList<Integer>());
		for(int i = 0; i < g.size(); ++i)
			for(int j : g.get(i))
				rev.get(j).add(i);
		
		Iterator<Integer> iter = list.descendingIterator();
		while(iter.hasNext()) {
			int node = iter.next();
			if(!marked[node]) {
				ArrayList<Integer> c = new ArrayList<Integer>();
				dfs(rev, node, marked, c);
				comp.add(c);
			}
		}
		return comp;
	}
	public static void dfs(ArrayList<ArrayList<Integer>> g, int node, boolean[]marked, List<Integer> list) {
		if(marked[node]) return;
		marked[node] = true;
		for(int next : g.get(node))
			dfs(g, next, marked, list);
		list.add(node);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
		HashMap<String, ArrayList<Integer>> umap = new HashMap<String, ArrayList<Integer>>();
		ArrayList<String> speaks = new ArrayList<String>();
		for(int i = 0; i < N; ++i) {
			String[]tokens = in.nextLine().split(" ");
			speaks.add(tokens[1]);
			for(int j = 1; j < tokens.length; ++j) {
				if(!umap.containsKey(tokens[j])) {
					umap.put(tokens[j], new ArrayList<Integer>());
				}
				umap.get(tokens[j]).add(i);
			}
		}
		for(int i = 0; i < N; ++i) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int ind : umap.get(speaks.get(i))) arr.add(ind);
			g.add(arr);
		}
		ArrayList<ArrayList<Integer>> c = scc(g);
		int max = 0;
		for(int i = 0; i < c.size(); ++i) {
			max = Math.max(max, c.get(i).size());
		}
		System.out.println(N - max);
	}

}
