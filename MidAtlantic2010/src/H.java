import java.util.*;
import java.awt.Point;
public class H {
	static class Edge {
		double cost;
		Point p1;
		Point p2;
		public Edge(Point a, Point b) {
			p1 = new Point(a);
			p2 = new Point(b);
			cost = Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
		}
	}
	final static double eps = 0.00001;
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int N;
		HashSet<Point> verts = new HashSet<Point>();
		PriorityQueue<Edge> qu = new PriorityQueue<Edge>(1, new Comparator<Edge>() {
			@Override
			public int compare(Edge arg0, Edge arg1) {
				if(Math.abs(arg0.cost - arg1.cost) < eps) {
					if(arg0.p1.x < arg1.p1.x)
						return -1;
					else if(arg0.p1.x > arg1.p1.x)
						return 1;
					if(arg0.p1.y < arg1.p1.y)
						return -1;
					else if(arg0.p1.y > arg1.p1.y)
						return 1;
					return 0;
				}
				if(arg0.cost < arg1.cost)
					return 1;
				else
					return -1;
			}
			
		});

		while((N = scanner.nextInt()) != 0) {
			verts.clear();
			qu.clear();
			for(int i = 0; i < N; ++i) {
				verts.add(new Point(scanner.nextInt(), scanner.nextInt()));
			}
			Point start = verts.iterator().next();
			verts.remove(start);
			for(Point p : verts) {
				qu.add(new Edge(start, p));
			}
			double cost = 0.0;
			while(!qu.isEmpty()) {
				Edge e = qu.poll();
				Point p = e.p2;
				if(verts.contains(p)) {
					cost += e.cost;
					verts.remove(p);
					for(Point asdf : verts) {
						qu.add(new Edge(p, asdf));
					}
				}
			}
			System.out.println(cost);
		}
		scanner.close();
	}
}
