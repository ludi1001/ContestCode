import java.util.*;
public class F {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		while(testCases > 0) {
			int n = in.nextInt();
			int[]arr = new int[n];
			for(int i = 0; i < n; ++i) arr[i] = in.nextInt();
			boolean[][]mat = new boolean[n][n];
			in.nextLine();
			for(int i = 0; i < n; ++i) {
				String line = in.nextLine();
				for(int j = 0; j < n; ++j) 
					mat[i][j] = line.charAt(j) == 'Y'; 
			}
			//get borders
			ArrayList<Integer> borders = new ArrayList<Integer>();
			for(int i = 0; i < n; ++i) {
				if(arr[i] == 0)
					continue;
				boolean border = false;
				for(int j = 0; j < n; ++j) {
					if(mat[i][j] && arr[j] == 0) {
						border = true;
						break;
					}
				}
				if(border)
					borders.add(i);
			}
			//setup adjacency list
			ArrayList<HashMap<Integer, Integer>> map = new ArrayList<HashMap<Integer, Integer>>();
			final int SOURCE = 2*n + 2;
			final int SINK = 2*n + 3;
			for(int i = 0; i <= SINK; ++i) map.add(new HashMap<Integer, Integer>());
			for(int i = 0; i < n; ++i) {
				if(arr[i] == 0)
					continue;
				map.get(SOURCE).put(2*i, arr[i]);
				map.get(2*i).put(2*i + 1, Integer.MAX_VALUE);
				map.get(2*i + 1).put(SINK, 1);
				for(int j = 0; j < n; ++j)
					if(mat[i][j]) map.get(2*i).put(2*j + 1, Integer.MAX_VALUE);
			}
			int max = 0;
			int regions = 0;
			for(int i = 0; i < n; ++i) {
				if(arr[i] == 0) continue;
				max += arr[i];
				++regions;
			}
			max = max/borders.size() + 1;
			int high = max;
			int low = 0;
			int mid;
			while(low < high) {
				mid = low + (high - low) / 2;
				//set drain limit
				for(int i : borders) map.get(2*i + 1).put(SINK, mid);
				if(mf(SOURCE, SINK, map) != borders.size()*mid + regions - borders.size())
					high = mid;
				else
					low = mid + 1;
			}
			System.out.println(low - 1);
			--testCases;
		}
	}
	public static int mf(int u, int v, ArrayList<HashMap<Integer, Integer>> in) {
		ArrayList<HashMap<Integer, Integer>> flow = new ArrayList<HashMap<Integer, Integer>>();
		for(int i = 0; i < in.size(); ++i) {
			flow.add(new HashMap<Integer, Integer>());
			for(int j:in.get(i).keySet())flow.get(i).put(j, 0);
		}
		int tot = 0;
		while(true) {
			Queue<Integer> q = new LinkedList<Integer>();
			int[]prev = new int[in.size()], fill = new int[in.size()];
			q.offer(u);
			Arrays.fill(prev, -1);
			prev[u] = -2;
			fill[u] = Integer.MAX_VALUE;
			while(prev[v] == -1) {
				if(q.isEmpty()) return tot;
				int p = q.poll();
				for(int i = 0; i < in.size(); ++i) if(p!=i&&prev[i]==-1&&((in.get(p).containsKey(i)&&flow.get(p).get(i) < in.get(p).get(i))||(in.get(i).containsKey(p)&&flow.get(i).get(p)>0))) {
					prev[i] = p;
					fill[i] = 0;
					if(in.get(p).containsKey(i))fill[i] += in.get(p).get(i) - flow.get(p).get(i);
					if(in.get(i).containsKey(p))fill[i] += flow.get(i).get(p);
					fill[i] = Math.min(fill[p], fill[i]);
					q.offer(i);
				}
			}
			tot += fill[v];
			int t = v;
			while(t != u) {
				int s = prev[t];
				int excess = fill[v];
				if(in.get(s).containsKey(t)) {
					excess = Math.max(flow.get(s).get(t) + fill[v] - in.get(s).get(t), 0);
					flow.get(s).put(t, flow.get(s).get(t)+fill[v]-excess);
				}
				if(excess>0)flow.get(t).put(s, flow.get(t).get(s) - excess);
				t=s;
			}
		}
	}
}
