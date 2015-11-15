import java.util.*;
public class G {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-- > 0) {
			int m = in.nextInt();
			int n = in.nextInt();
			ArrayList<HashMap<Integer, Integer>> map = new ArrayList<HashMap<Integer, Integer>>();
			int u = n;
			int v = n + 1;
			for(int i = 0; i < n + m + 2; ++i) {
				map.add(new HashMap<Integer, Integer>());
			}
			for(int i = 0; i < n; ++i)
				map.get(u).put(i, 1);
			for(int i = 0; i < m; ++i) {
				int first = in.nextInt();
				int last = in.nextInt();
				while(first != last) {
					map.get(first).put(i + n + 2, 1);
					++first;
					if(first >= n)
						first = 0;
				}
				map.get(last).put(i + n + 2, 1);
				map.get(i + n + 2).put(v, 1);
			}
			System.out.println(map);
			System.out.println(maxFlow(u,v,map) == m ? "YES" : "NO");
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
	 public static int maxFlow2(int u,int v,ArrayList<HashMap<Integer,Integer>> in){
		             ArrayList<HashMap<Integer,Integer>> flow=new ArrayList<HashMap<Integer,Integer>>();
		             for(int i=0;i<in.size();i++){
		                     flow.add(new HashMap<Integer,Integer>());
		                     for(int j:in.get(i).keySet())flow.get(i).put(j, 0);
		             }
		             int tot=0;
		             while(true){
		                     Queue<Integer>q=new LinkedList<Integer>();
		                     int[] prev=new int[in.size()],fill=new int[in.size()];
		                     q.offer(u);
		                    Arrays.fill(prev, -1);
		                     fill[u]=Integer.MAX_VALUE;
		                     while(prev[v]==-1){
		                             if(q.isEmpty())return tot;
		                             int p=q.poll();
		                             for(int i=0;i<in.size();i++)if(p!=i&&prev[i]==-1&&((in.get(p).containsKey(i)&&flow.get(p).get(i)<in.get(p).get(i))||(in.get(i).containsKey(p)&&flow.get(i).get(p)>0))){
		                                     prev[i]=p;
		                                     fill[i]=0;
		                                     if(in.get(p).containsKey(i))fill[i]+=in.get(p).get(i)-flow.get(p).get(i);
		                                     if(in.get(i).containsKey(p))fill[i]+=flow.get(i).get(p);
		                                     Math.min(fill[p], fill[i]);
		                                     q.offer(i);
		                             }
		                     }
		                     tot+=fill[v];
		                     int t=v;
		                     while(t!=u){
		                             int s=prev[t];
		                             int excess=fill[t];
		                             if(in.get(s).containsKey(t)){
		                                     excess=Math.max(flow.get(s).get(t)+fill[t]-in.get(s).get(t), 0);
		                                     flow.get(s).put(t,flow.get(t).get(s)+fill[t]-excess);
		                             }
		                             if(excess>0)flow.get(t).put(s, flow.get(t).get(s)-excess);
		                             t=s;
		                     }
		            }
		     }
	/*public static int maxFlow2(int u,int v,ArrayList<HashMap<Integer,Integer>> in){
		ArrayList<HashMap<Integer,Integer>> flow=new ArrayList<HashMap<Integer,Integer>>();
		for(int i=0;i<in.size();i++){
		flow.add(new HashMap<Integer,Integer>());
		for(int j:in.get(i).keySet())flow.get(i).put(j, 0);
		}
		int tot=0;
		while(true){
		Queue<Integer>q=new LinkedList<Integer>();
		int[] prev=new int[in.size()],fill=new int[in.size()];
		q.offer(u);
		Arrays.fill(prev, -1);
		fill[u]=Integer.MAX_VALUE;
		while(prev[v]==-1){
		if(q.isEmpty())return tot;
		int p=q.poll();
		for(int i=0;i<in.size();i++)if(p!=i&&prev[i]==-
		1&&((in.get(p).containsKey(i)&&flow.get(p).get(i)<in.get(p).get(i))||(in.get(i).containsKey(p
		)&&flow.get(i).get(p)>0))){
		prev[i]=p;
		fill[i]=0;
		if(in.get(p).containsKey(i))fill[i]+=in.get(p).get(i)-
		flow.get(p).get(i);
		if(in.get(i).containsKey(p))fill[i]+=flow.get(i).get(p);
		Math.min(fill[p], fill[i]);
		q.offer(i);
		}
		}
		tot+=fill[v];
		int t=v;
		while(t!=u){
		int s=prev[t];
		int excess=fill[t];
		if(in.get(s).containsKey(t)){
		excess=Math.max(flow.get(s).get(t)+fill[t]-in.get(s).get(t), 0);
		System.out.println(flow.get(s));
		System.out.println(flow.get(t).get(s));
		fill[t] = fill[t];
		flow.get(s).put(t,flow.get(t).get(s)+fill[t]-excess);
		}
		if(excess>0)flow.get(t).put(s, flow.get(t).get(s)-excess);
		t=s;
		}
		}
		}*/
}
