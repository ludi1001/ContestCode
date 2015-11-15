import java.util.*;
public class C {
	static class Pair implements Comparable<Pair> {
		int a;
		int b;
		public int compareTo(Pair p) {
			int diff = a - p.a;
			if(diff != 0) return diff;
			return b - p.b;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Pair[]e = new Pair[n];
		for(int i = 0; i < n; ++i) {
			e[i] = new Pair();
			e[i].a = in.nextInt();
			e[i].b = in.nextInt();
		}
		Arrays.sort(e);
		int day = 1;
		for(int i = 0; i < n; ++i) {
			if(day == e[i].a || day == e[i].b) continue;
			else if(day < e[i].b)
				day = e[i].b;
			else if(day < e[i].a)
				day = e[i].a;
		}
		System.out.println(day);
	}
}
