import java.util.*;
import java.awt.Point;
public class FlippingCoins {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int tc = 1; tc <= t; ++tc) {
			int r = in.nextInt(), c = in.nextInt(); in.nextLine();
			boolean[][]tail = new boolean[r][c];
			for(int i = 0; i < r; ++i) {
				String line = in.nextLine();
				for(int j = 0; j < c; ++j) {
					if(line.charAt(j) == 'T')
						tail[i][j] = true;
				}
			}
			
			boolean[][]sol = new boolean[r][c];
			Queue<Point> qu = new LinkedList<Point>();
			HashSet<Point> visited = new HashSet<Point>();
			for(int i = 0; i < c; ++i) {
				if(tail[0][i]) {
					qu.add(new Point(0, i));
					visited.add(new Point(0,i));
				}
				if(tail[r-1][i]) {
					qu.add(new Point(r-1, i));
					visited.add(new Point(r-1,i));
				}
			}
			for(int i = 0; i < r; ++i) {
				if(tail[i][0]) {
					qu.add(new Point(i, 0));
					visited.add(new Point(i, 0));
				}
				if(tail[i][c-1]) {
					qu.add(new Point(i, c-1));
					visited.add(new Point(i, c-1));
				}
			}
			int[][]dir = { {0, 1}, {1, 0}, {-1, 0}, {0, -1} };
			while(!qu.isEmpty()) {
				Point p = qu.poll();
				sol[p.x][p.y] = true;
				for(int i = 0; i < 4; ++i) {
					int x = p.x + dir[i][0];
					int y = p.y + dir[i][1];
					if(x < 0 || y < 0 || x >= r || y >= c) continue;
					if(tail[x][y] && !visited.contains(new Point(x,y))) {
						qu.add(new Point(x,y));
						visited.add(new Point(x,y));
					}
				}
			}
			
			System.out.println(tc);
			for(int i = 0; i < r; ++i) {
				for(int j = 0; j < c; ++j)
					System.out.print(sol[i][j] ? 'T' : 'H');
				System.out.println();
			}
		}
	}
}
