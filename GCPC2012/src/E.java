import java.util.*;
public class E {
	/**
	 * @param args
	 */
	private static class Pair implements Comparable<Pair> {
		String s1;
		String s2;
		Pair(String a, String b) {
			s1 = a;
			s2 = b;
		}
		public int compareTo(Pair p) {
			int diff = s1.compareTo(p.s1);
			if(diff != 0)
				return diff;
			return s2.compareTo(p.s2);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t > 0) {
			int n = in.nextInt();
			long shift = 0;
			HashMap<String, Long> map1 = new HashMap<String, Long>();
			HashMap<String, Long> map2 = new HashMap<String, Long>();
			in.nextLine();
			for(int i = 0; i < n; ++i) {
				in.next();
				int m = in.nextInt();
				for(int j = 0; j < m; ++j) {
					String s = in.next();
					if(!map1.containsKey(s))
						map1.put(s, 0L);
					map1.put(s, map1.get(s) | (1L << shift));
				}
				m = in.nextInt();
				for(int j = 0; j < m; ++j) {
					String s = in.next();
					if(!map2.containsKey(s))
						map2.put(s, 0L);
					map2.put(s, map2.get(s) | (1L << shift));
				}
				shift += 1;
			}
			ArrayList<Pair> list = new ArrayList<Pair>();
			for(String s1 : map1.keySet()) {
				for(String s2 : map2.keySet()) {
					if(map1.get(s1).equals(map2.get(s2)))
						list.add(new Pair(s1, s2));
				}
			}
			Collections.sort(list);
			for(Pair p : list) {
				System.out.println("(" + p.s1 + ", " + p.s2 + ")");
			}
			if(t > 1)
				System.out.println();
			--t;
		}
	}

}
