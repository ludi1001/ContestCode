import java.util.*;
public class G2 {
	public static String p(int s) {
		String str = "";
		for (int i = 0; i < 5; ++i) {
			if ((s & (1 << i)) > 0) str += "1";
			else str += "0";
		}
		return str;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[]t = new int[N];
		for(int i = 0; i < N; ++i)
			t[i] = in.nextInt();
		
		final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		TreeSet<Integer> qu = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer s0, Integer s1) {
				if(map.get(s0) != map.get(s1)) return map.get(s0) - map.get(s1);
				return s0 - s1;
			}
		});
		int end = (1 << (N + 1)) - 1;
		map.put(0, 0);
		qu.add(0);
		while(!qu.isEmpty()) {
			int s = qu.pollFirst();
			if (s == end) {
				System.out.println(map.get(s));
				break;
			}
			//System.out.println(p(s) + " : " + map.get(s));
			int cloak = 1 << N;
			int cs = (s & cloak) >> N;
			for(int i = 0; i < N; ++i) {
				int is = (s & (1 << i)) >> i;
				if(cs != is) continue;
				int ns = s ^ (1 << i) ;
				ns ^= cloak;
				int nt = map.get(s) + t[i];
				if(!map.containsKey(ns)) {
					map.put(ns, nt);
					qu.add(ns);
					//System.out.println("pusha " + p(ns) + " : " + map.get(ns));
				} else if(map.get(ns) > nt) {
					qu.remove(ns);
					map.put(ns, nt);
					qu.add(ns);
					//System.out.println("pusha " + p(ns) + " : " + map.get(ns));
				}
				//System.out.println("consider " + p(ns) + " : " + map.get(ns));
				for(int j = i + 1; j < N; ++j) {
					int js = (s & (1 << j )) >> j;
					if(cs != js) continue;
					ns = s ^ (1 << j) ^ (1 << i);
					ns ^= cloak;
					nt = map.get(s) + Math.max(t[i], t[j]);
					if(!map.containsKey(ns)) {
						map.put(ns, nt);
						qu.add(ns);
						//System.out.println("pushz " + p(ns) + " : " + map.get(ns));
					} else if(map.get(ns) > nt) {
						qu.remove(ns);
						map.put(ns, nt);
						qu.add(ns);
						//System.out.println("pushz " + p(ns) + " : " + map.get(ns));
					}
					//System.out.println("consider " + p(ns) + " : " + map.get(ns));
				}
			}
		}
	}

}
