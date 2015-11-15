import java.util.*;
import java.awt.Point;
public class I {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int X = in.nextInt();
			if (X == -1) break;
			int Y = in.nextInt();
			int T = in.nextInt();
			int L = in.nextInt();
			int W = in.nextInt();
			boolean[][]marked = new boolean[X][Y];
			Queue<Point> qu = new LinkedList<Point>();
			while(L-->0) {
				int x = in.nextInt()-1, y = in.nextInt()-1;
				marked[x][y] = true;
				qu.add(new Point(x,y));
			}
			qu.add(null);
			while(W-->0) {
				int sx = in.nextInt()-1, sy = in.nextInt()-1, ex = in.nextInt()-1, ey = in.nextInt()-1;
				int dx = (ex-sx)/Math.max(1, Math.abs(ex-sx));
				int dy = (ey-sy)/Math.max(1, Math.abs(ey-sy));
				int x = sx, y = sy;
				while(x != ex || y != ey) {
					marked[x][y] = true;
					x += dx;
					y += dy;
				}
				marked[ex][ey] = true;
			}
			int count = 0;
			while(!qu.isEmpty()) {
				Point p = qu.poll();
				if (p == null) {
					if (--T <= 0) break;
					qu.add(null);
					continue;
				}
				++count;
				for (int i = 0; i < 4; ++i) {
					int[][]dirs = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };
					int x = p.x + dirs[i][0];
					int y = p.y + dirs[i][1];
					if (x < 0 || x >= X || y < 0 || y >= Y) continue;
					if (marked[x][y]) continue;
					marked[x][y] = true;
					qu.add(new Point(x, y));
				}
			}
			System.out.println(count);
		}
	}
}