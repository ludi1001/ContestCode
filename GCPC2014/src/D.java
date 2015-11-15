import java.util.*;
public class D {
	static class Edge {
		int cost;
		int dest;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			int N = in.nextInt();
			int E = in.nextInt();
			ArrayList<ArrayList<Edge>> grev = new ArrayList<ArrayList<Edge>>();
			int[]val = new int[N];
			for(int i = 0; i < N; ++i) {
				val[i] = in.nextInt();
				grev.add(new ArrayList<Edge>());
			}
			for(int i = 0; i < E; ++i) {
				Edge e = new Edge();
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				int c = in.nextInt();
				e.dest = a;
				e.cost = c;
				grev.get(b).add(e);
			}
			int[]totval = new int[N];
			int[]dir = new int[N];
			Arrays.fill(dir, -1);
			int[]top = topologicalSort(grev);
			for(int v : top) {
				totval[v] += val[v];
				for(Edge e : grev.get(v)) {
					int nval = totval[v] - e.cost;
					if(totval[e.dest] < nval) {
						totval[e.dest] = nval;
						dir[e.dest] = v;
					}
				}
			}
			int n = 0;
			int C = 1;
			while(dir[n] != -1) {
				n = dir[n];
				++C;
			}
			System.out.println(totval[0] + " " + C);
			n = 0;
			System.out.print(1);
			while(dir[n] != -1) {
				n = dir[n];
				System.out.print(" " + (n+1));
			}
			System.out.println();
		}
	}
	public static int[] topologicalSort(ArrayList<ArrayList<Edge>> in){
		int[] rev=new int[in.size()],out=new int[in.size()];
		for(ArrayList<Edge> i:in)for(Edge j:i)rev[j.dest]++;
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=0;i<in.size();i++)if(rev[i]==0)q.add(i);
		if(q.isEmpty())return new int[0];
		int count=0;
		while(!q.isEmpty()){
			int t=q.poll();
			for(Edge i:in.get(t)){
				rev[i.dest]--;
				if(rev[i.dest]==0)q.add(i.dest);
			}
			out[count++]=t;
		}
		if(count<out.length)return new int[0];
		return out;
	}
}
