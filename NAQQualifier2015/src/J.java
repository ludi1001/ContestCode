import java.util.*;
public class J {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
		for(int j = 0; j < N; ++j) {
			String[]tokens = in.nextLine().split(" ");
			if(!map.containsKey(tokens[0]))
				map.put(tokens[0], new HashSet<String>());
			for (int i = 1; i < tokens.length; i++) {
				map.get(tokens[0]).add(tokens[i]);
				if(!map.containsKey(tokens[i]))
					map.put(tokens[i], new HashSet<String>());
				map.get(tokens[i]).add(tokens[0]);
			}
		}
		String[]tokens = in.nextLine().split(" ");
		String start = tokens[0];
		String end = tokens[1];
		HashSet<String> visited = new HashSet<String>();
		HashMap<String, String> path = new HashMap<String, String>();
		visited.add(start);
		Queue<String> qu = new LinkedList<String>();
		qu.add(start);
		while(!qu.isEmpty()) {
			String node = qu.poll();
			if (node.equals(end)) {
				break;
			}
			if (map.containsKey(node)) {
				for (String next : map.get(node)) {
					if (!visited.contains(next)) {
						visited.add(next);
						path.put(next, node);
						qu.add(next);
					}
				}
			}
		}
		if (!path.containsKey(end)) {
			System.out.println("no route found");
		}
		else {
			Stack<String> stack = new Stack<String>();
			String node = end;
			int c = 0;
			while(!node.equals(start)) {
				stack.push(node);
				node = path.get(node);
				++c;
			}
			stack.push(node);
			while(!stack.isEmpty()) {
				System.out.print(stack.pop());
				if(c-->0) System.out.print(" ");
			}
			System.out.println();
		}
	}

}
