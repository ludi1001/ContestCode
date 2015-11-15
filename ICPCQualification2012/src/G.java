import java.awt.Point;
import java.util.*;
public class G {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		ArrayList<Point[]> rects = new ArrayList<Point[]>();
		ArrayList<int[]> circles = new ArrayList<int[]>();
		for(int i = 0; i < m; ++i) {
			String type = in.next();
			if(type.equals("rectangle")) {
				Point[]p = new Point[2];
				p[0] = new Point();
				p[1] = new Point();
				p[0].x = in.nextInt();
				p[0].y = in.nextInt();
				p[1].x = in.nextInt();
				p[1].y = in.nextInt();
				rects.add(p);
			}
			else {
				int[]c = new int[3];
				c[0] = in.nextInt();
				c[1] = in.nextInt();
				c[2] = in.nextInt();
				circles.add(c);
			}
		}
		int n = in.nextInt();
		while(n > 0) {
			int x = in.nextInt();
			int y = in.nextInt();
			int count = 0;
			for(int i = 0; i < rects.size(); ++i) {
				Point[]r = rects.get(i);
				if(x >= r[0].x && x <= r[1].x) {
					if(y >= r[0].y && y <= r[1].y) {
						++count;
					}
				}
			}
			for(int i = 0; i < circles.size(); ++i) {
				int[]c = circles.get(i);
				int distSquared = (x - c[0]) * (x - c[0]) + (y - c[1]) * (y - c[1]);
				if(distSquared <= c[2] * c[2]) {
					++count;
				}
			}
			System.out.println(count);
			--n;
		}
	}

}
