import java.util.*;
public class C {
	public static int[] topologicalSort(ArrayList<ArrayList<Integer>> in){
		int[] rev=new int[in.size()],out=new int[in.size()];
		for(ArrayList<Integer> i:in)for(int j:i)rev[j]++;
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=0;i<in.size();i++)if(rev[i]==0)q.add(i);
		if(q.isEmpty())return new int[0];
		int count=0;
		while(!q.isEmpty()){
		int t=q.poll();
		for(int i:in.get(t)){
		rev[i]--;
		if(rev[i]==0)q.add(i);
		}
		out[count++]=t;
		}
		if(count<out.length)return new int[0];
		return out;
		}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0){
			int K = in.nextInt(), M = in.nextInt(), P = in.nextInt();
			System.out.print(K + " ");
			ArrayList<ArrayList<Integer>> g = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> gr = new ArrayList<ArrayList<Integer>>();
			int[]order = new int[M];
			for(int i = 0; i < M; ++i) {
				g.add(new ArrayList<Integer>());
				gr.add(new ArrayList<Integer>());
			}
			while(P-->0) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				g.get(u).add(v);
				gr.get(v).add(u);
			}
			int[]sorted = topologicalSort(g);
			for(int v : sorted) {
				if(gr.get(v).size() == 0)
					order[v] = 1;
				else {
					int max = 0;
					int count = 0;
					for(int u : gr.get(v)) {
						if(order[u] > max) {
							max = order[u];
							count = 1;
						}
						else if(order[u] == max)
							++count;
					}
					order[v] = max;
					if(count > 1)
						++order[v];
				}
			}
			System.out.println(order[sorted[sorted.length-1]]);
		}
	}

}
