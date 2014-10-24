import java.util.*;
public class ProblemE {
	public static final double eps = 0.000001;
	public static void addEdge(char a, char b, double val, HashMap<Character, HashMap<Character, Double>> graph) {
		 if(!graph.containsKey(a)) {
			 graph.put(a, new HashMap<Character, Double>());
		 }
		 if(!graph.get(a).containsKey(b))
			 graph.get(a).put(b, -1.0);
		 if(!graph.containsKey(b)) {
			 graph.put(b, new HashMap<Character, Double>());
		 }
		 if(!graph.get(b).containsKey(a))
			 graph.get(b).put(a, -1.0);
		 double d = graph.get(a).get(b);
		 if(d > 0.0) {
			 double newVal = d*val/(d + val);
			 graph.get(a).put(b, newVal);
			 graph.get(b).put(a, newVal);
		 }
		 else {
			 graph.get(a).put(b, val);
			 graph.get(b).put(a, val);
		 }
	}
	public static void removeEdge(char a, char b, HashMap<Character, HashMap<Character, Double>> graph) {
		graph.get(a).remove(b);
		graph.get(b).remove(a);
	}
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int count = 1;
		HashMap<Character, HashMap<Character, Double>> graph = new HashMap<Character, HashMap<Character, Double>>();
		while(true) {
			graph.clear();
			int N = scanner.nextInt();
			if(N == 0)
				break;
			for(int i = 0; i < N; ++i) {
				 char a = scanner.next().charAt(0);
				 char b = scanner.next().charAt(0);
				 addEdge(a, b, scanner.nextInt(), graph);
			}
			while(true) {
				boolean change = false;
				for(Character a : graph.keySet()) {
					if(a == 'A' || a == 'Z')
						continue;
					Set<Character> s = graph.get(a).keySet();
					if(s.size() == 2) {
						change = true;
						Iterator<Character> iter = s.iterator();
						char first = iter.next();
						char second = iter.next();
						double newVal = graph.get(a).get(first) + graph.get(a).get(second);
						removeEdge(a, first, graph);
						removeEdge(a, second, graph);
						addEdge(first, second, newVal, graph);
					}
				}
				if(!change)
					break;
			}
			double equiv = -1.0;
			boolean failed = false;
			for(Character a : graph.keySet()) {
				if(a == 'A' || a == 'Z')
					continue;
				if(graph.get(a).keySet().size() > 0) {
					failed = true;
					break;
				}
			}
			if(!failed) {
				if(graph.get('A').containsKey('Z'))
					equiv = graph.get('A').get('Z');
			}
			System.out.printf("Circuit %d: %.3f\n", count, equiv);
			++count;
		}
		scanner.close();
	}
}
