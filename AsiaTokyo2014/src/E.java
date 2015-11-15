import java.util.*;
import java.awt.Point;
public class E {
	// N, E, S, W
	public static int[]dx = { 0, 1, 0, -1};
	public static int[]dy = { 1, 0, -1, 0};
	public static HashSet<Point> dfs(boolean[][]map, Point p, int d, int dir, int fdir) {
		HashSet<Point> possible = new HashSet<Point>();
		int odir = (dir + 2) % 4;
		if (d == 0) {
			int x = p.x + dx[fdir];
			int y = p.y + dy[fdir];
			if (fdir == odir) return possible;
			if ((x < 0 || x > 100 || y < 0 || y > 100 || !map[x][y]) && (dir != fdir)) return possible;
			possible.add(new Point(p));
			return possible;
		}
		for (int i = 0; i < 4; ++i) {
			if (i == odir) continue;
			Point np = new Point(p.x + dx[i], p.y + dy[i]);
			if (np.x < 0 || np.x > 100 || np.y < 0 || np.y > 100) continue;
			if (map[np.x][np.y]) {
				possible.addAll(dfs(map, np, d-1, i, fdir));
			}
		}
		return possible;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int x0 = 2*in.nextInt();
		int y0 = 2*in.nextInt();
		int t = in.nextInt();
		boolean[][]map = new boolean[101][101];
		for (int i = 0; i < n; ++i) {
			int xs = 2*in.nextInt();
			int ys = 2*in.nextInt();
			int xe = 2*in.nextInt();
			int ye = 2*in.nextInt();
			int dx, dy;
			if (xs == xe) {
				dx = 0;
				if (ys < ye) dy = 1;
				else dy = -1;
			} else {
				dy = 0;
				if (xs < xe) dx = 1;
				else dx = -1;
			}
			int x = xs;
			int y = ys;
			while( x != xe || y != ye) {
				map[x][y] = true;
				x += dx;
				y += dy;
			}
			map[x][y] = true;
		}
		
		HashSet<Point> possible = new HashSet<Point>();
		possible.add(new Point(x0,y0));
		int lastdir = -1;
		while(t-->0) {
			int d = 2*in.nextInt();
			String dirstr = in.next();
			int dir = -100;
			if (dirstr.equals("N")) dir = 0;
			else if(dirstr.equals("E")) dir = 1;
			else if(dirstr.equals("S")) dir = 2;
			else if(dirstr.equals("W")) dir = 3;
			
			HashSet<Point> newp = new HashSet<Point>();
			for (Point p : possible) {
				if (lastdir >= 0) {
					newp.addAll(dfs(map, p, d, lastdir, dir));
				} else {
					for (int i = 0; i < 4; ++i) {
						newp.addAll(dfs(map, p, d, i, dir));
					}
				}
			}
			possible = newp;
			
			lastdir = dir;
		}
		
		ArrayList<Point> plist = new ArrayList<>(possible);
		Collections.sort(plist, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				if (o1.x != o2.x) return o1.x - o2.x;
				return o1.y - o2.y;
			}
		});
		for (Point p : plist) {
			System.out.println((p.x/2) + " " + (p.y/2));
		}
	}

}
