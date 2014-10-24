import java.util.*;
public class F {
	static class Tuple implements Comparable<Tuple> {
		int fuel;
		int city;
		Tuple(int f, int c) {
			fuel = f;
			city = c;
		}
		@Override
		public int compareTo(Tuple o) {
			if(this.fuel != o.fuel)
				return this.fuel - o.fuel;
			return this.city - o.city;
		}
		@Override
		public boolean equals(Object o) {
			return this.compareTo((Tuple)o) == 0;
		}
		@Override
		public int hashCode() {
			return this.fuel*10000 + this.city;
		}
		@Override
		public String toString() {
			return this.city + " " + this.fuel;
		}
	}
	public static void main(String[]args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> prices = new ArrayList<Integer>();
		ArrayList<HashMap<Integer, Integer>> g = new ArrayList<HashMap<Integer, Integer>>();
		
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		
		for(int i = 0; i < n; ++i)
			prices.add(scanner.nextInt());
		
		for(int i = 0; i < n; ++i)
			g.add(new HashMap<Integer, Integer>());
		for(int i = 0; i < m; ++i) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int d = scanner.nextInt();
			
			g.get(u).put(v, d);
			g.get(v).put(u, d);
		}
		
		int q = scanner.nextInt();
		for(int queries = 0; queries < q; ++queries) {
			final HashMap<Tuple, Integer> costmap = new HashMap<Tuple, Integer>();
			TreeSet<Tuple> qu = new TreeSet<Tuple>(new Comparator<Tuple>() {
				public int compare(Tuple n1, Tuple n2) {
					int c1 = costmap.get(n1);
					int c2 = costmap.get(n2);
					if(c1 != c2)
						return c1 - c2;
					return n1.compareTo(n2);
				}
			});
			int c = scanner.nextInt();
			int s = scanner.nextInt();
			int e = scanner.nextInt();
			costmap.put(new Tuple(0, s), 0);
			qu.add(new Tuple(0, s));
			Tuple end = null;
			while(!qu.isEmpty()) {
				Tuple node = qu.pollFirst();
				if(node.city == e) {
					end = node;
					break;
				}
				//System.out.println("Examining " + node + "(" + costmap.get(node) + ")");
				if(node.fuel < c) {
					Tuple t = new Tuple(node.fuel + 1, node.city);
					int newc = costmap.get(node) + prices.get(node.city);
					if(costmap.containsKey(t)) {
						if(costmap.get(t) > newc) {
							qu.remove(t);
							costmap.put(t, newc);
							qu.add(t);
						}
					}
					else {
						costmap.put(t, newc);
						qu.add(t);
					}
				}
				for(int next : g.get(node.city).keySet()) {
					int dist = g.get(node.city).get(next);
					if(dist > node.fuel) continue;
					Tuple t = new Tuple(node.fuel - dist, next);
					if(costmap.containsKey(t)) {
						if(costmap.get(t) > costmap.get(node)) {
							qu.remove(t);
							costmap.put(t, costmap.get(node));
							qu.add(t);
						}
					}
					else {
						costmap.put(t, costmap.get(node));
						qu.add(t);
					}
				}
			}
			
			if(end != null)
				System.out.println(costmap.get(end));
			else
				System.out.println("impossible");
		}
	}
}
