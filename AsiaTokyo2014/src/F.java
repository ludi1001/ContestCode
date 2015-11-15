import java.util.*;
import java.awt.Point;
public class F {
	public static boolean unique(Point e, ArrayList<ArrayList<Integer>> mstg, ArrayList<HashMap<Integer, Integer>> g) {
		HashSet<Integer> visited = new HashSet<Integer>();
		visited.add(e.x);
		Queue<Integer> qu = new LinkedList<Integer>();
		qu.add(e.x);
		// Run BFS without the edge to figure out connected component.
		while(!qu.isEmpty()) {
			int n = qu.poll();
			for (int p : mstg.get(n)) {
				if (n == e.x && p == e.y) continue;
				if (n == e.y && p == e.x) continue;
				if (!visited.contains(p)) {
					visited.add(p);
					qu.add(p);
				}
			}
		}
		// Determine if there is an equal cost way of adding an edge.
		int cost = g.get(e.x).get(e.y);
		for (int u : visited) {
			for (int v : g.get(u).keySet()) {
				if (visited.contains(v)) continue;
				if (cost != g.get(u).get(v)) continue;
				if (u == e.x && v == e.y) continue;
				if (v == e.x && u == e.y) continue;
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		
		final ArrayList<HashMap<Integer, Integer>> g = new ArrayList<>();
		for (int i = 0; i < N; ++i)
			g.add(new HashMap<Integer, Integer>());
		for (int i = 0; i < M; ++i) {
			int s = in.nextInt() - 1;
			int d = in.nextInt() - 1;
			int c = in.nextInt();
			g.get(s).put(d, c);
			g.get(d).put(s, c);
		}
		
		ArrayList<Point> mst = new ArrayList<Point>();
		ArrayList<Point> pe = new ArrayList<Point>();
		HashSet<Integer> visited = new HashSet<Integer>();
		PriorityQueue<Point> pq = new PriorityQueue<Point>(g.size(), new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (g.get(o1.x).get(o1.y)-g.get(o2.x).get(o2.y)>0) return 1;
				else return -1;
			}
		});
		
		// Find MST.
		visited.add(0);
		for (int i : g.get(0).keySet())pq.offer(new Point(0,i));
		int cost = 0;
		while(visited.size()<g.size()){
			Point t=pq.poll();
			cost = Math.max(cost, g.get(t.x).get(t.y));
			if(visited.contains(t.y))continue;
			visited.add(t.y);
			mst.add(t);
			for(int i:g.get(t.y).keySet()) 
				if(!visited.contains(i))pq.offer((new Point(t.y,i)));
		}
		// Filter out edges that could potentially be in alternative MST.
		for (int i = 0; i < N; ++i) {
			for (int j : g.get(i).keySet() ) {
				if (g.get(i).get(j) <= cost) {
					pe.add(new Point(i, j));
				}
			}
		}
		
		// Construct MST graph.
		ArrayList<ArrayList<Integer>> mstg = new ArrayList<>();
		for (int i = 0; i < N; ++i) mstg.add(new ArrayList<Integer>());
		for (Point e : mst) {
			mstg.get(e.x).add(e.y);
			mstg.get(e.y).add(e.x);
		}
		
		// Construct graph with edges with weight <= maximum weight of edge in MST.
		ArrayList<HashMap<Integer, Integer>> g2 = new ArrayList<>();
		for (int i = 0; i < N; ++i) g2.add(new HashMap<Integer,Integer>());
		for (Point e : pe) {
			g2.get(e.x).put(e.y, g.get(e.x).get(e.y));
			g2.get(e.y).put(e.x, g.get(e.x).get(e.y));
		}

		ArrayList<Point> ans = new ArrayList<Point>();
		for (Point e : mst) {
			if (unique(e, mstg, g2)) {
				ans.add(e);
			}
		}

		int sum = 0;
		for (Point e : ans) sum += g.get(e.x).get(e.y);
		System.out.print(ans.size() + " " + sum);
	}
}
