import java.util.Scanner;
public class H {
	public static boolean works(double[]d, double dtotal, double maxweight, int W) {
		for(int start = 0; start < d.length; ++start) {
			int cur = start;
			int wused = 0;
			do {
				int s = cur;
				double dist = 0;
				do {
					++cur;
					cur %= d.length;
					if (cur == start) return true;
					dist = d[cur] - d[s];
					if (dist < 0) dist += dtotal;
				} while(dist <= maxweight);
				++wused;
			} while(wused < W);
		}
		return false;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int W = in.nextInt();
			if (W == 0) break;
			int N = in.nextInt();
			int D = in.nextInt();
			int[]divisors = new int[D];
			for(int i = 0; i < D; ++i)
				divisors[i] = in.nextInt();
			
			boolean[]works = new boolean[N];
			for(int i = 0; i < D; ++i) {
				works[0] = true;
				int ind = divisors[i];
				while(ind < N) {
					works[ind] = true;
					ind += divisors[i];
				}
			}
			int count = 0;
			for(int i = 0; i < N; ++i) {
				if(works[i])count++;
			}
			double[]d = new double[count];
			int last = 0;
			int ind = 1;
			d[0] = 0;
			double dtotal = 0;
			for (int i = 1; i < N; ++i) {
				if(works[i]) {
					double theta = 2*Math.PI/N*(i-last);
					dtotal += Math.sqrt(2*1000*1000*(1 - Math.cos(theta)));
					d[ind] = dtotal;
					last = i;
					++ind;
				}
			}
			dtotal += Math.sqrt(2*1000*1000*(1 - Math.cos(2*Math.PI/N*(N-last))));
			
			double lo = 0;
			double hi = dtotal;
			double mid = (hi + lo)/2;
			double eps = 1e-8;
			while(Math.abs(hi - lo) > eps) {
				mid = (hi + lo)/2;
				if (works(d, dtotal, mid, W)) {
					hi = mid;
				} else {
					lo = mid;
				}
			}
			System.out.printf("%.1f\n", lo+2000);
		}
	}

}
