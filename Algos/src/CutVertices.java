import java.util.*;
public class CutVertices {
	private HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> lowlink = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> parent = new HashMap<Integer, Integer>();
	private int ind = 0;
	private int cutvertices = 0;
	public int articulationPoints(String[]routers) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < routers.length; ++i) {
			graph.add(new ArrayList<Integer>());
			for(String s : routers[i].split(" "))
				graph.get(i).add(Integer.parseInt(s));
		}
		
		index.clear();
		lowlink.clear();
		ind = 0;
		cutvertices = 0;
		for(int i = 0; i < graph.size(); ++i) {
			if(!index.containsKey(i)) {
				parent.put(i, -1);
				tarjan(i, graph);
			}
		}
		int count = 0;
		for(int i : parent.values())
			if(i == 0) ++count;
		if(count <= 1) --cutvertices;
		return cutvertices;
	}
	public void tarjan(int k, ArrayList<ArrayList<Integer>> graph) {
		index.put(k, ind++);
		lowlink.put(k, index.get(k));
		boolean iscut = false;
		for(int j : graph.get(k)) {
			if(!index.containsKey(j)) {
				parent.put(j, k);
				tarjan(j, graph);
				if(lowlink.get(j) >= index.get(k))
					iscut = true;
				if(lowlink.get(k) > lowlink.get(j))
					lowlink.put(k, lowlink.get(j));
			}
			else if(parent.get(j) != k && index.get(j) < index.get(k)){
				if(index.get(j) < lowlink.get(k))
					lowlink.put(k, index.get(j));
			}
		}
		if(iscut)
			++cutvertices;
	}
}

//tarjan's algorithm for articulation vertices
