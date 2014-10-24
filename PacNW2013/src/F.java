import java.util.*;
public class F {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String[]map = new String[100001];
		for(int n = 2; n < map.length; ++n) {
			int m = (int)Math.ceil(Math.sqrt(n));
			HashSet<Integer> factors = new HashSet<Integer>();
			for(int f = 1; f <= m; ++f) {
				if(n % f == 0) {
					factors.add(f);
					if(f != n/f)
						factors.add(n/f);
				}
			}
			factors.remove(n);
			int sum = 0;
			for(int f : factors) sum += f;
			if(sum == n) {
				ArrayList<Integer> sorted_factors = new ArrayList<Integer>(factors);
				Collections.sort(sorted_factors);
				String s = n + " = " + sorted_factors.get(0);
				for(int i = 1; i < sorted_factors.size(); ++i)
					s += " + " + sorted_factors.get(i);
				map[n] = s;
				//System.out.println(s);
			}
			else {
				map[n] = n + " is NOT perfect.";
			}
		}
		int n = 0;
		while((n = in.nextInt()) != -1)
			System.out.println(map[n]);
	}

}
