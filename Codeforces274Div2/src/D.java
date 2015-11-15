import java.util.*;
public class D {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int l = in.nextInt();
		int x = in.nextInt();
		int y = in.nextInt();
		HashSet<Integer> amap = new HashSet<Integer>();
		HashSet<Integer> xmap = new HashSet<Integer>();
		HashSet<Integer> ymap = new HashSet<Integer>();
		for(int i = 0; i < n; ++i) {
			int a = in.nextInt();
			amap.add(a);
			if(a+x <= l)
				xmap.add(a + x);
			if(a-x >= 0)
				xmap.add(a - x);
			if(a+y <= l)
				ymap.add(a + y);
			if(a-y >= 0)
				ymap.add(a - y);
		}
		HashSet<Integer> ax = new HashSet<Integer>(amap);
		ax.retainAll(xmap);
		HashSet<Integer> ay = new HashSet<Integer>(amap);
		ay.retainAll(ymap);
		if(ax.size() == 0 && ay.size() == 0) {
			HashSet<Integer> xy = new HashSet<Integer>(xmap);
			xy.retainAll(ymap);
			if(xy.size() == 0) {
				System.out.println(2);
				System.out.println(x + " " + y);
			}
			else {
				System.out.println(1);
				for(int v : xy) {
					System.out.println(v); break;
				}
			}
		}
		else if(ax.size() == 0) {
			System.out.println(1);
			System.out.println(x);
		}
		else if(ay.size() == 0) {
			System.out.println(1);
			System.out.println(y);
		}
		else {
			System.out.println(0);
		}
	}

}
