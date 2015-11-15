import java.util.*;
public class I {
	static class Pair implements Comparable<Pair> {
		String str;
		int count;
		Pair(String s, int c) {
			count = c;
			str = s;
		}
		@Override
		public int compareTo(Pair p) {
			int diff = p.count - this.count;
			if(diff != 0) return diff;
			return str.compareTo(p.str);
		}
		public String toString() {
			return "(" + str + ", " + count + ")";
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		LinkedList<HashMap<String, Integer>> qu = new LinkedList<HashMap<String, Integer>>();
		while(in.hasNext()) {
			String line = in.nextLine();
			if(line.trim().isEmpty()) continue;
			if(line.equals("<text>")) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				String token;
				while(!(token = in.next()).equals("</text>")) {
					if(token.length() < 4) continue;
					if(!map.containsKey(token)) map.put(token, 0);
					map.put(token, map.get(token) + 1);
				}
				qu.add(map);
				if(qu.size() > 7)
					qu.poll();
			}
			else {
				String[]tokens = line.split(" ");
				int top = Integer.parseInt(tokens[1]);
				int maxTokenCount = 0;
				System.out.println("<top " + top + ">");
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				for(HashMap<String, Integer> m : qu) {
					for(String t : m.keySet()) {
						if(!map.containsKey(t)) map.put(t, 0);
						map.put(t, map.get(t) + m.get(t));
					}
				}
				ArrayList<Pair> arr = new ArrayList<Pair>();
				for(String t : map.keySet()) {
					arr.add(new Pair(t, map.get(t)));
				}
				Collections.sort(arr);
				int c = 0;
				while(top-->0) {
					maxTokenCount = arr.get(c).count;
					System.out.println(arr.get(c).str + " " + arr.get(c).count);
					++c;
				}
				while(c < arr.size() && arr.get(c).count == maxTokenCount) {
					System.out.println(arr.get(c).str + " " + arr.get(c).count);
					++c;				
				}
				System.out.println("</top>");
			}
		}
	}

}


/*HashSet<String> used = new HashSet<String>();
while(top >= 0) {
	HashMap<String, Integer> tmap = new HashMap<String, Integer>();
	String maxToken = null;
	int maxCount = 0;
	int i = 0;
	for(ArrayList<Pair> arr : qu) {
		if(index[i] < arr.size()) {
			int j = index[i];
			int count = arr.get(index[i]).count;
			while(j < arr.size() && arr.get(j).count == count) {
				Pair p = arr.get(j);
				if(used.contains(p.str)) {
					++j;
					continue;
				}
				if(!tmap.containsKey(p.str)) tmap.put(p.str, 0);
				tmap.put(p.str, tmap.get(p.str) + p.count);
				++j;
			}
		}
		++i;
	}
	for(String t : tmap.keySet()) {
		if(tmap.get(t) > maxCount) {
			maxToken = t;
			maxCount = tmap.get(t);
		}
		else if(tmap.get(t) == maxCount) {
			if(t.compareTo(maxToken) < 0)
				maxToken = t;
		}
	}
	maxTokenCount = 0;
	i = 0;
	for(ArrayList<Pair> arr : qu) {
		if(index[i] < arr.size()) {
			int j = index[i];
			int count = arr.get(index[i]).count;
			while(j < arr.size() && arr.get(j).count == count) {
				
				if(used.contains(arr.get(j).str)) {
					++j;
					continue;
				}
				if(arr.get(j).str.equals(maxToken)) {
					maxTokenCount += arr.get(j).count;
				}
				++j;
			}
			if(j == index[i]) ++index[i];
		}
		++i;
	}
	if(top != 0)
		System.out.println(maxToken + " " + maxTokenCount);
	else if(lastTokenCount == maxTokenCount) {
		System.out.println(maxToken + " " + maxTokenCount);
		++top;
	}
	used.add(maxToken);
	lastTokenCount = maxTokenCount;
	--top;
}*/