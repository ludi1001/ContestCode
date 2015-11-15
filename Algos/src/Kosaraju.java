import java.util.*;
//proving equivalences
public class Kosaraju {
	public static ArrayList<ArrayList<Integer>> scc(ArrayList<ArrayList<Integer>> g) {
		boolean[]marked = new boolean[g.size()];
		LinkedList<Integer> list = new LinkedList<Integer>();
		ArrayList<ArrayList<Integer>> comp = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
		
		//order by post value
		for(int i = 0; i < g.size(); ++i)
			dfs(g, i, marked, list);
		Arrays.fill(marked, false);
		
		//create transpose
		for(int i = 0; i < g.size(); ++i)
			rev.add(new ArrayList<Integer>());
		for(int i = 0; i < g.size(); ++i)
			for(int j : g.get(i))
				rev.get(j).add(i);
		
		//find components
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
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-->0) {
			int n = in.nextInt();
			int m = in.nextInt();
			ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < n; ++i)
				g.add(new ArrayList<Integer>());
			while(m-->0) g.get(in.nextInt()-1).add(in.nextInt() - 1);
			
			ArrayList<ArrayList<Integer>> comp = scc(g);
			if(comp.size() == 1) {
				System.out.println(0);
				continue;
			}
			int[]indeg = new int[comp.size()];
			int[]outdeg = new int[comp.size()];
			int[]compIndex = new int[n];
			for(int i = 0; i < comp.size(); ++i) for(int j : comp.get(i))
				compIndex[j] = i;
			
			for(int i = 0; i < g.size(); ++i) for(int j : g.get(i)) 
				if(compIndex[i] != compIndex[j]) {
					++indeg[compIndex[j]];
					++outdeg[compIndex[i]];
				}
			
			int sources = 0;
			int sinks = 0;
			for(int i = 0; i < comp.size(); ++i) {
				if(indeg[i] == 0) ++sources;
				if(outdeg[i] == 0) ++sinks;
			}
			System.out.println(Math.max(sources, sinks));
		}
	}
}
