import java.util.*;
public class B2 {
	static boolean reachEnd = false;
	static boolean cycle = false;
	static int count = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < n-1; ++i) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			int k = in.nextInt();
			for(int j = 0; j < k; ++j) {
				int next = in.nextInt() - 1;
				a.add(next);
			}
			g.add(a);
		}
		g.add(new ArrayList<Integer>());
		
		ArrayList<ArrayList<Integer>> s = scc(g);
		int sinks = 0;
		for(ArrayList<Integer> comp : s) {
			HashSet<Integer> set = new HashSet<Integer>(comp);
			boolean sink = true;
			outer:
			for(int node : comp) {
				for(int p : g.get(node)) {
					if(!set.contains(p)) {
						sink = false;
						break outer;
					}
				}
			}
			if(sink) ++sinks;
		}
		if(sinks == 1 && reachEnd)
			System.out.print("PARDON ");
		else
			System.out.print("PRISON ");
		if(s.size() != count || cycle)
			System.out.println("UNLIMITED");
		else
			System.out.println("LIMITED");
	}
	public static ArrayList<ArrayList<Integer>> scc(ArrayList<ArrayList<Integer>> g) {
		boolean[]marked = new boolean[g.size()];
		LinkedList<Integer> list = new LinkedList<Integer>();
		ArrayList<ArrayList<Integer>> comp = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
		
		//order by post value
		//for(int i = 0; i < g.size(); ++i)
		//	dfs(g, i, marked, list);
		dfs(g, 0, marked, list);
		if(marked[g.size()-1]) reachEnd = true;
		for(int i = 0; i < g.size(); ++i)
			if(marked[i]) ++count;
		
		//create transpose
		for(int i = 0; i < g.size(); ++i)
			rev.add(new ArrayList<Integer>());
		for(int i = 0; i < g.size(); ++i)
			if(marked[i]) for(int j : g.get(i))
				if(marked[j]) rev.get(j).add(i);
		Arrays.fill(marked, false);
		
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
		for(int next : g.get(node)) {
			if(next == node)
				cycle = true;
			dfs(g, next, marked, list);
		}
		list.add(node);
	}
}
