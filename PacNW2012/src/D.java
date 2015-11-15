import java.util.*;
public class D {
	static int cross(Point v1, Point v2) { //v1 x v2
		return v1.nx*v2.ny - v2.nx*v1.ny;
	}
	static boolean containsAngle(Point p1, Point p2, Point p3) {
		if(cross(p1, p2) >= 0) {
			return cross(p1, p3) >= 0 && cross(p2, p3) <= 0;
		}
		else {
			//check that p3 is not in [p2, p1]
			return !(cross(p2, p3) > 0 && cross(p1, p3) < 0);
		}
	}
	static class Point implements Comparable<Point> {
		int x;
		int y;
		int nx;
		int ny;
		double angle;
		public Point(int x, int y, int nx, int ny) {
			this.x = x;
			this.y = y;
			this.nx = nx;
			this.ny = ny;
		}
		@Override
		public int compareTo(Point arg0) {
			//check halves, lower half is always larger
			if(arg0.ny * this.ny < 0) {
				//opposite signs, check which is negative
				if(ny < 0) return 1;
				return -1;
			}
			return -cross(this, arg0);
		}
		public String toString() {
			return this.x + " " + this.y + " ";// + this.nx + " " + this.ny + " " + Math.atan2(ny, nx);
		}
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int N, W, H;
			N = in.nextInt();
			W = in.nextInt();
			H = in.nextInt();
			if(N == 0) break;
			
			ArrayList<Point> points = new ArrayList<Point>();
			for(int i = 0; i < N; ++i) {
				int x = in.nextInt();
				int y = in.nextInt();
				int nx = 2 * x - W;
				int ny = 2 * y - H; 
				if(H % 2 == 1) { //H is odd so use x-axis to divide points in halves
					points.add(new Point(x, y, nx, ny));
				}
				else { //W is odd: swap x and y axes
					points.add(new Point(x, y, ny, nx));
				}
			}
			Collections.sort(points);
			//System.out.println(points);
			
			int last_index = N - 1;
			int first_index = 0;
			for(first_index = 0; first_index < N/2; ++first_index) {
				Point p1 = points.get(last_index);
				Point p2 = points.get(first_index);
				
				p1 = new Point(p1.x, p1.y, -p1.nx, -p1.ny);
				p2 = new Point(p2.x, p2.y, -p2.nx, -p2.ny);
				
				Point q1 = points.get(first_index + N/2 - 1);
				Point q2 = points.get(first_index + N/2);
				
				if(containsAngle(p1, p2, q1) || containsAngle(p1, p2, q2) || containsAngle(q1, q2, p1) || containsAngle(q1, q2, p2))
					break;
				
				last_index = first_index;
			}
			for(int i = first_index; i < first_index + N/2; ++i) {
				System.out.println(points.get(i));
			}
		}
	}

}
