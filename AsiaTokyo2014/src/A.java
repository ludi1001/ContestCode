import java.util.*;
public class A {
	public static int dist(int a, int b, int N) {
		ArrayList<Integer> la = new ArrayList<Integer>();
		ArrayList<Integer> lb = new ArrayList<Integer>();
		for (int i = 0; i < N; ++i) {
			int m = 1 << i;
			int bit = (a & m) >> i;
			if (bit == 1)
				la.add(i);
		}
		for (int i = 0; i < N; ++i) {
			int m = 1 << i;
			int bit = (b & m) >> i;
			if (bit == 1)
				lb.add(i);
		}
		int dist = 0;
		if (la.size() != lb.size()) return Integer.MAX_VALUE;
		for (int i = 0; i < la.size(); ++i) {
			dist += Math.abs(la.get(i) - lb.get(i));
		}
		return dist;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();

		int a = 0;
		for (int i = 0; i < N; ++i) {
			a <<= 1;
			a |= in.nextInt();
		}
		
		int bit = 0;
		int target = 0;
		for (int i = 0; i < M; ++i) {
			int s = in.nextInt();
			for (int j = 0; j < s; ++j) {
				target <<= 1;
				target |= bit;
			}
			if (bit == 0) bit = 1;
			else bit = 0;
		}
		int d1 = dist(a, target, N);
		int d2 = dist(a, ~target, N);
		System.out.println(Math.min(d1, d2));
	}

}
