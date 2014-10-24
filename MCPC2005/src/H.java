import java.util.*;
public class H {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		int caseNum = 1;
		int[][]map = new int[20][20];
		ArrayList<HashMap<Integer, Integer>> graph = new ArrayList<HashMap<Integer, Integer>>();
		//n index in node
		//n + 1 index out node
		while(testCases > 0) {
			int n = in.nextInt();
			int d = in.nextInt(); in.nextLine();
			int c = 0;
			graph.clear();
			int ind = 0;
			for(int i = 0; i < n; ++i) {
				String line = in.nextLine();
				c = line.length();
				for(int j = 0; j < c; ++j) {
					int p = line.charAt(j) - '0';
					if(p == 0)
						map[i][j] = -1;
					else {
						map[i][j] = ind;
						graph.add(new HashMap<Integer, Integer>());
						graph.add(new HashMap<Integer, Integer>());
						graph.get(2*ind).put(2*ind + 1, p);
						++ind;
					}
				}
			}
			graph.add(new HashMap<Integer, Integer>());
			graph.add(new HashMap<Integer, Integer>());
			int last = graph.size() - 2;
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < c; ++j) {
					if(map[i][j] >= 0) {
						for(int k = -d; k <= d; ++k) {
							for(int m = -d; m <= d; ++m) {
								if(k == 0 && m == 0)
									continue;
								if(Math.abs(m) + Math.abs(k) > d)
									continue;
								int x = i + k;
								int y = j + m;
								if(x < 0 || x >= n || y < 0 || y >= c)
									graph.get(2*map[i][j]+1).put(last + 1, Integer.MAX_VALUE);
								else if(map[x][y] >= 0)
									graph.get(2*map[i][j]+1).put(2*map[x][y], Integer.MAX_VALUE);
							}
						}
					}
				}
			}
			for(int i = 0; i < n; ++i) {
				String line = in.nextLine();
				for(int j = 0; j < c; ++j) {
					if(line.charAt(j) == 'L')
						graph.get(last).put(2*map[i][j], 1);
				}
			}
			/*for(int i = 0; i < graph.size(); ++i) {
				System.out.print(i + ": ");
				for(int k : graph.get(i).keySet())
					System.out.println(k + ", " + graph.get(i).get(k));
				System.out.println();
			}*/
			System.out.println(graph);
			int left = graph.get(last).size() - maxFlow(last, last + 1, graph);
			System.out.print("Case #" + caseNum + ": ");
			if(left == 0)
				System.out.print("no lizard was");
			else if(left == 1)
				System.out.print("1 lizard was");
			else
				System.out.print(left + " lizards were");
			System.out.println(" left behind.");
			--testCases;
			++caseNum;
		}
	}
	public static int maxFlow(int u, int v, ArrayList<HashMap<Integer, Integer>> in) {
		ArrayList<HashMap<Integer, Integer>> flow = new ArrayList<HashMap<Integer, Integer>>();
		for(int i = 0; i < in.size(); ++i) {
			flow.add(new HashMap<Integer, Integer>());
			for(int j : in.get(i).keySet())
				flow.get(i).put(j, 0);
		}
		int tot = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		int[]prev = new int[in.size()], fill = new int[in.size()];
		while(true) {
			q.clear();
			Arrays.fill(fill, 0);
			q.offer(u);
			Arrays.fill(prev, -1);
			prev[u] = -2;
			fill[u] = Integer.MAX_VALUE;
			while(prev[v] == -1) {
				if(q.isEmpty())
					return tot;
				int p = q.poll();
				for(int i = 0; i < in.size(); ++i) {
					if(p!=i && prev[i] == -1 && ((in.get(p).containsKey(i) && flow.get(p).get(i) < in.get(p).get(i))
							|| (in.get(i).containsKey(p) && flow.get(i).get(p) > 0))) {
						prev[i] = p;
						fill[i] = 0;
						if(in.get(p).containsKey(i))
							fill[i] += in.get(p).get(i) - flow.get(p).get(i);
						if(in.get(i).containsKey(p))
							fill[i] += flow.get(i).get(p);
						fill[i] = Math.min(fill[p], fill[i]);
						q.offer(i);
					}
				}
			}
			tot += fill[v];
			int t = v;
			while(t != u) {
				int s = prev[t];
				int excess = fill[v];
				if(in.get(s).containsKey(t)) {
					excess = Math.max(flow.get(s).get(t)+fill[v]-in.get(s).get(t), 0);
					flow.get(s).put(t, flow.get(s).get(t) + fill[v] - excess);
				}
				if(excess > 0)
					flow.get(t).put(s,  flow.get(t).get(s) - excess);
				t = s;
			}
		}
	}
}
