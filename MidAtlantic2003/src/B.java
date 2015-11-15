import java.util.*;
public class B {

	/**
	 * @param args
	 */
	static class IntStringPair {
		String name;
		double weight;
		public IntStringPair() {
			weight = -1;
			name = null;
		}
		public void set(double w, String s) {
			if(weight == -1 || w < weight) {
				weight = w;
				name = s;
			}
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		HashMap<String, HashMap<String, IntStringPair>>[] graph = new HashMap[3];
		for(int i = 0; i < 3; ++i) {
			graph[i] = new HashMap<String, HashMap<String, IntStringPair>>();
		}
		int cities = in.nextInt();
		for(int i = 0; i < cities; ++i) {
			String city = in.next();
			for(int j = 0; j < 3; ++j) {
				graph[j].put(city, new HashMap<String, IntStringPair>());
			}
		}
		int roads = in.nextInt();in.nextLine();
		for(int i = 0; i < roads; ++i) {
			String[]stuff = in.nextLine().split(" ");
			String road = stuff[0];
			int j = 1;
			String last = stuff[j++];
			ArrayList<String> blah = new ArrayList<String>();
			blah.add(last);
			while(j < stuff.length) {
				double distWeight = Double.parseDouble(stuff[j++]);
				double timeWeight = Double.parseDouble(stuff[j++]);
				String city = stuff[j++];
				for(int k = 0; k < 3; ++k) {
					if(!graph[k].get(last).containsKey(city)) {
						graph[k].get(last).put(city, new IntStringPair());
						graph[k].get(city).put(last,  new IntStringPair());
					}
				}
				graph[0].get(last).get(city).set(distWeight, road);
				graph[1].get(last).get(city).set(timeWeight, road);
				graph[0].get(city).get(last).set(distWeight, road);
				graph[1].get(city).get(last).set(timeWeight, road);
				last = city;
				blah.add(city);
			}
			for(String c : blah) {
				for(String d : blah) {
					if(!c.equals(d)) {
						if(!graph[2].get(c).containsKey(d))
							graph[2].get(c).put(d, new IntStringPair());
						graph[2].get(c).get(d).set(1, road);
					}
				}
			}
		}
		
		while(in.hasNextLine()) {
			String type = in.next();
			String start = in.next();
			String end = in.next();
			HashMap<String, HashMap<String, IntStringPair>> map = null;
			if(type.equals("distance"))
				map = graph[0];
			else if(type.equals("time"))
				map = graph[1];
			else
				map = graph[2];
			
			HashMap<String, String> last = new HashMap<String, String>();
			final HashMap<String, Double> dist = new HashMap<String, Double>();
			HashSet<String> visited = new HashSet<String>();
			TreeSet<String> qu = new TreeSet<String>(new Comparator<String>() {

				@Override
				public int compare(String arg0, String arg1) {
					double diff = dist.get(arg0) - dist.get(arg1);
					if(diff > 0)
						return 1;
					if(diff < 0)
						return -1;
					return arg0.compareTo(arg1);
				}
				
			});
			dist.put(start, 0.0);
			qu.add(start);
			visited.add(start);
			last.put(start, null);
			while(!qu.isEmpty()) {
				String c = qu.pollFirst();
				if(c.equals(end))
					break;
				for(String next : map.get(c).keySet()) {
					if(!visited.contains(next) || map.get(c).get(next).weight + dist.get(c) < dist.get(next)) {
						if(visited.contains(next))
							qu.remove(next);
						dist.put(next, dist.get(c) + map.get(c).get(next).weight);
						last.put(next, c);
						qu.add(next);
						visited.add(next);
					}
				}
			}
			
			System.out.println("from " + start);
			Stack<String> stack = new Stack<String>();
			Stack<String> rname = new Stack<String>();
			String c = end;
			while(last.get(c) != null) {
				stack.push(c);
				//System.out.println(c);
				String p =c;
				c = last.get(c);
				String road = map.get(c).get(p).name;
				rname.push(road);
				boolean changed = false;
				while(!c.equals(start) && map.get(c).get(p).name.equals(road)) {
					p = c;
					changed = true;
					c = last.get(c);
				}
				if(changed)
					c = p;
				//System.out.println(p);
			}
			while(!stack.isEmpty()) {
				System.out.println(rname.pop() + " to " + stack.pop());
			}
		}
	}

}
