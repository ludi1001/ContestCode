import java.util.*;
public class I {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][]keys = { {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
				         {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', '0'},
				         {'z', 'x', 'c', 'v', 'b', 'n', 'm', '0', '0', '0'}
		};
		int[][]dist = new int[127][127];
		for(int i = 0; i < keys.length; ++i) {
			for(int j = 0; j < keys[0].length; ++j) {
				if(keys[i][j] == '0') break;
				for(int k = 0; k < keys.length; ++k) {
					for(int m = 0; m < keys[0].length; ++m) {
						if(keys[k][m] == '0') break;
						dist[keys[i][j]][keys[k][m]] = Math.abs(i - k) + Math.abs(j - m);
					}
				}
			}
		}
		int t = in.nextInt();
	    final HashMap<String, Integer> d = new HashMap<String, Integer>();
		while(t > 0) {
			String s = in.next();
			int k = in.nextInt(); in.nextLine();
			d.clear();
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 0; i < k; ++i) {
				String l = in.nextLine();
				list.add(l);
				int sum = 0;
				for(int j = 0; j < l.length(); ++j)
					sum += dist[s.charAt(j)][l.charAt(j)];
				d.put(l, sum);
			}
			Collections.sort(list, new Comparator<String>() {
				public int compare(String s1, String s2) {
					int diff = d.get(s1) - d.get(s2);
					if(diff != 0)
						return diff;
					return s1.compareTo(s2);
				}
			});
			for(String str : list) {
				System.out.println(str + " " + d.get(str));
			}
			--t;
		}
	}

}
