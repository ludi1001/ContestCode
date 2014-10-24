import java.awt.Point;
import java.util.*;
public class D {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		while(testCases-- > 0) {
			int n = in.nextInt();
			ArrayList<Point> verts = new ArrayList<Point>();
			Point source = new Point(in.nextInt(), in.nextInt());
			for(int i = 0; i < n; ++i)
				verts.add(new Point(in.nextInt(), in.nextInt()));
			Point end = new Point(in.nextInt(), in.nextInt());
			verts.add(source); verts.add(end);
			
			boolean success = false;
			Queue<Point> qu = new LinkedList<Point>();
			Set<Point> visited = new HashSet<Point>();
			qu.add(source);
			visited.add(source);
			while(!qu.isEmpty()) {
				Point node = qu.poll();
				//System.out.println(node);
				if(node.equals(end)) {
					success = true;
					break;
				}
				for(Point p : verts) {
					if(!visited.contains(p)) {
						int dist = Math.abs(p.x - node.x) + Math.abs(p.y - node.y);
						if(dist <= 1000) {
							qu.add(p);
							visited.add(p);
						}
					}
				}
			}
			if(success)
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
}
