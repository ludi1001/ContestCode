import java.util.*;
public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[]h = new int[n];
		for(int i = 0; i < n; ++i)
			h[i] = in.nextInt();
		int m = 0;
		ArrayList<String> steps = new ArrayList<String>();
		while(k-->0) {
			int minh = 100000;
			int minhindex = 0;
			int maxh = -1;
			int maxhindex = 0;
			for(int i = 0; i < n; ++i) {
				if(h[i] < minh) {
					minhindex = i;
					minh = h[i];
				}
				if(h[i] > maxh) {
					maxh = h[i];
					maxhindex = i;
				}
			}
			if(maxh == minh) break;
			h[maxhindex]--;
			h[minhindex]++;
			steps.add((maxhindex+1) + " " + (minhindex+1));
			++m;
		}
		int minh = 100000;
		int minhindex = 0;
		int maxh = -1;
		int maxhindex = 0;
		for(int i = 0; i < n; ++i) {
			if(h[i] < minh) {
				minhindex = i;
				minh = h[i];
			}
			if(h[i] > maxh) {
				maxh = h[i];
				maxhindex = i;
			}
		}
		int s = maxh-minh;
		System.out.println(s + " " + m);
		for(String step : steps)
			System.out.println(step);
	}

}
