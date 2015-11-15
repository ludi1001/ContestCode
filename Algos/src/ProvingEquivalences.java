//tarjan's strongly connected compoents
import java.util.*;
public class ProvingEquivalences {
	private static HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> lowlink = new HashMap<Integer, Integer>();
	private static Stack<Integer> stack = new Stack<Integer>();
	private static int ind = 0;
	private static int components = 0;
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		int testCases = scanner.nextInt();
		while(testCases > 0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			graph.clear();
			index.clear();
			lowlink.clear();
			stack.clear();
			ind = 0;
			for(int i = 0; i < n; ++i)
				graph.add(new ArrayList<Integer>());
			while(m > 0) {
				int s1 = scanner.nextInt() - 1;
				int s2 = scanner.nextInt() - 1;
				graph.get(s1).add(s2);
				--m;
			}
			components = 0;
			for(int i = 0; i < n; ++i) {
				if(!index.containsKey(i))
					tarjan(i, graph);
			}
			System.out.println(components);
			--testCases;
		}
		scanner.close();
	}
	public static void tarjan(int k, ArrayList<ArrayList<Integer>> graph) {
		//System.out.println("Pushing " + (k + 1));
		stack.push(k);
		index.put(k, ind++);
		lowlink.put(k, index.get(k));
		for(int j : graph.get(k)) {
			//System.out.println("Checking " + (k + 1) + ", " + (j + 1));
			if(!index.containsKey(j)) {
				tarjan(j, graph);
				if(lowlink.get(k) > lowlink.get(j))
					lowlink.put(k, lowlink.get(j));
			}
			else if(stack.contains(j)){
				if(index.get(j) < lowlink.get(k))
					lowlink.put(k, index.get(j));
			}
		}
		if(lowlink.get(k) == index.get(k)) {
			//System.out.println("Comp " + (k + 1));
			while(stack.peek() != k) {
				//System.out.println(stack.peek() + 1);
				stack.pop();
			}
			stack.pop();
			++components;
		}
		//System.out.println("Returning");
	}
}
