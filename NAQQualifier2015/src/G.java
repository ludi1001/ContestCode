import java.util.*;
public class G {
	public static int state(int sleft, int sright, int cloak) {
		return (sleft << 15) | sright | (cloak << 30);
	}
	public static void print(int s) {
		int mask = (1 << 15) - 1;
		int sleft = (s & (mask << 15)) >> 15;
		int sright = s & mask;
		System.out.println("q: " + sleft + ":" + sright);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[]counts = new int[N];
		for (int i = 0; i < N; ++i) counts[i] = in.nextInt();
		final HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		TreeSet<Integer> qu = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer s0, Integer s1) {
				if(map.get(s0) != map.get(s1)) return map.get(s0) - map.get(s1);
				return s0 - s1;
			}
		});
		int initial = 0;
		for (int i = 0; i < N; ++i) {
			initial += 1 << i;
		}
		int finals = initial;
		map.put(state(initial, 0, 0), 0);
		visited.add(state(initial, 0, 0));
		qu.add(state(initial, 0, 0));
		int mask = (1 << 15) - 1;
		while(!qu.isEmpty()) {
			int s = qu.pollFirst();
			if ((s & mask) == finals) {
				System.out.println(map.get(s));
				break;
			}
			int sleft = (s & (mask << 15)) >> 15;
			int sright = s & mask;
			System.out.println(sleft + ":" + sright + ":" + map.get(s));
			if ((s & (1 << 30)) == 0) {
				for(int i = 0; i < N; ++i) {
					int im = 1 << i;
					if ((sleft & im) == 0) continue;
					int time = map.get(s) + counts[i];
					int ns = state(sleft & ~im, sright | im, 1);
					print(ns);
					if(!visited.contains(ns) || !qu.contains(ns)) {
						map.put(ns, time);
						qu.add(ns);
						visited.add(ns);
					} else if(map.get(ns) > time) {
						qu.remove(ns);
						map.put(ns, time);
						qu.add(ns);
					}
					for(int j = i + 1; j < N; ++j) {
						int jm = 1 << j;
						if ((sleft & jm) == 0) continue;
						int nleft = sleft;
						nleft &= ~im;
						nleft &= ~jm;
						int nright = sright;
						nright |= (im | jm);
						ns = state(nleft, nright, 1);
						if(!visited.contains(ns)|| !qu.contains(ns)) {
							map.put(ns, time);
							qu.add(ns);
							visited.add(ns);
						} else if(map.get(ns) > time) {
							qu.remove(ns);
							map.put(ns, time);
							qu.add(ns);
						}
					}
				}
			} else {
				for(int i = 0; i < N; ++i) {
					int im = 1 << i;
					if ((sright & im) == 0) continue;
					int time = map.get(s) + counts[i];
					int ns = state(sleft | im, sright & ~im, 0);
					print(ns);
					if(!visited.contains(ns)|| !qu.contains(ns)) {
						map.put(ns, time);
						qu.add(ns);
						visited.add(ns);
					} else if(map.get(ns) > time) {
						qu.remove(ns);
						map.put(ns, time);
						qu.add(ns);
					}
					for(int j = i + 1; j < N; ++j) {
						int jm = 1 << j;
						if ((sright & jm) == 0) continue;
						int nright = sright;
						nright &= ~im;
						nright &= ~jm;
						int nleft = sleft;
						nleft |= (im | jm);
						time = map.get(s) + Math.max(counts[i], counts[j]);
						ns = state(nleft, nright, 0);
						if(!visited.contains(ns)|| !qu.contains(ns)) {
							map.put(ns, time);
							qu.add(ns);
							visited.add(ns);
						} else if(map.get(ns) > time) {
							qu.remove(ns);
							map.put(ns, time);
							qu.add(ns);
						}
					}
				}
			}
		}
	}

}
