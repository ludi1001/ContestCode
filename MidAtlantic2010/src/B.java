import java.util.*;
import java.awt.Point;
public class B {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int N = in.nextInt();
			int A = in.nextInt();
			int B = in.nextInt();
			if(N == 0 && A == 0 && B == 0)
				break;
			ArrayList<Point> list = new ArrayList<Point>();
			int cost = 0;
			for(int i = 0; i < N; ++i) {
				int k = in.nextInt();
				int adist = in.nextInt();
				int bdist = in.nextInt();
				cost += Math.min(adist, bdist) * k;
				list.add(new Point(adist - bdist, k));
			}
			Collections.sort(list, new Comparator<Point>() {
				public int compare(Point p1, Point p2) {
					if(p1.x != p2.x)
						return p1.x - p2.x;
					if(p1.y != p2.y)
						return p1.y - p2.y;
					return 0;
				}
			});
			for(int j = 0; j < list.size(); ++j) {
				Point p = list.get(j);
				if(p.x > 0) break;
				if(p.y <= A) A -= p.y;
				else {
					B -= p.y - A;
					cost += (p.y - A) * -p.x;
					A = 0;
				}
			}
			for(int j = list.size() - 1; j >= 0; --j) {
				Point p = list.get(j);
				if(p.x <= 0) break;
				if(p.y <= B) B -= p.y;
				else {
					A -= p.y - B;
					cost += (p.y - B) * p.x;
					B = 0;
				}
			}
			System.out.println(cost);
		}
	}

}
