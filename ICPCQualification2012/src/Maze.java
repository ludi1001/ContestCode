
import java.util.*;
public class Maze {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[]nums = new int[n];
		int min = 0; //min
 		int max = 0; //max
		for(int i = 0; i < n; ++i) {
			nums[i] = in.nextInt();
			if(nums[i] < nums[min])
				min = i;
			if(nums[i] > nums[max])
				max = i;
		}
		ArrayList<HashMap<Integer, Integer>> list = new ArrayList<HashMap<Integer, Integer>>();
		for(int i = 0; i < n; ++i)
			list.add(new HashMap<Integer, Integer>());
		for(int i = 0; i < n; ++i) {
			for(int j = i + 1; j < n; ++j) {
				int g = gcd(i, j);
				if(g > 1) {
					list.get(i).put(j, g);
					list.get(j).put(i, g);
				}
			}
		}
		System.out.println(maxflow(min, max, list));
	}
	public static int gcd(int a, int b) {
		while(b != 0) {
			int t = a % b;
			a = b;
			b = t;
		}
		return a;
	}
	
	public static int maxflow(int u, int v, ArrayList<HashMap<Integer, Integer>> in) {
		ArrayList<HashMap<Integer, Integer>> flow = new ArrayList<HashMap<Integer, Integer>>();
		for(int i = 0; i < in.size(); ++i) {
			flow.add(new HashMap<Integer, Integer>());
			for(int j : in.get(i).keySet()) flow.get(i).put(j,0);
		}
		int tot = 0;
		while(true) {
			Queue<Integer>q = new LinkedList<Integer>();
			int[]prev = new int[in.size()], fill = new int[in.size()];
			q.offer(u);
			Arrays.fill(prev,  -1);
			fill[u] = Integer.MAX_VALUE;
			while(prev[v] == -1) {
				if(q.isEmpty())return tot;
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
			tot+=fill[v];
			int t = v;
			while(t!=u) {
				int s = prev[t];
				int excess = fill[t];
				if(in.get(s).containsKey(t)) {
					excess = Math.max(flow.get(s).get(t) + fill[t] - in.get(s).get(t), 0);
					flow.get(s).put(t, flow.get(t).get(s) + fill[t] - excess);
				}
				if(excess>0)flow.get(t).put(s, flow.get(t).get(s)-excess);
				t = s;
			}
			//System.out.println(tot);
		}
	}
}
