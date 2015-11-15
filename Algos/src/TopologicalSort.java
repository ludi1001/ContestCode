import java.util.*;
public class TopologicalSort {
	//does not detect cycles
	//my version:
	/**
	 * topological sort
	 * @param adjlist adjacency list
	 * @return array indexed by vertex giving order, i.e. arr[0] = 1 means that the first vertex has order 1
	 */
	public int[] topsort(ArrayList<ArrayList<Integer>> adjlist) {
		int cur = adjlist.size() - 1;
		int[]order = new int[adjlist.size()];
		boolean[]visited = new boolean[adjlist.size()];
		for(int i = 0; i < adjlist.size(); ++i)
			if(!visited[i]) cur = dfs(i, adjlist, order, cur, visited);
		return order;
	}
	private int dfs(int node, ArrayList<ArrayList<Integer>> adjlist, int[] order, int cur, boolean[]visited) {
		visited[node] = true;
		for(int next : adjlist.get(node))
			if(!visited[next]) cur = dfs(next, adjlist, order, cur, visited);
		order[node] = cur;
		return cur - 1;
	}
	//Kevin's version: array outputs vertex in order, i.e. arr[0] is first vertex, arr[1] is second, etc
	//so arr[0] = 2 means vertex 2 is first
	public int[] topologicalSort(ArrayList<ArrayList<Integer>> in){
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
		TopologicalSort tp = new TopologicalSort();
		ArrayList<ArrayList<Integer>> mat = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 7; ++i) mat.add(new ArrayList<Integer>());
		mat.get(0).add(1); mat.get(0).add(4);
		mat.get(1).add(2); mat.get(1).add(5);
		mat.get(3).add(4);
		mat.get(4).add(5); mat.get(4).add(6);
		
		int[]order = tp.topologicalSort(mat);
		for(int i = 0; i < order.length; ++i)
			System.out.println(order[i]);
	}

}
