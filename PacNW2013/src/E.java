import java.util.*;
import java.awt.Point;
public class E {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0){
			int k = in.nextInt(), w = in.nextInt(), h = in.nextInt();
			in.nextLine();
			int[]values = new int[26];
			while(k-->0){
				String[]t = in.nextLine().split(" ");
				values[t[0].charAt(0)-'A'] = Integer.parseInt(t[1]);
			}
			char[][]map = new char[w][h];
			Point start = null;
			for(int j = 0; j < h; ++j) {
				int i = 0;
				for(char c : in.nextLine().toCharArray()) {
					if(c == 'E') start = new Point(i,j);
					map[i++][j] = c;
				}
			}
			final int[][] dist = new int[w][h];
			for(int j = 0; j < h; ++j) for(int i = 0; i < w; ++i) dist[i][j] = Integer.MAX_VALUE >> 2;
			TreeSet<Point> qu = new TreeSet<Point>(new Comparator<Point>() {
				public int compare(Point p1, Point p2) {
					if(dist[p1.x][p1.y] != dist[p2.x][p2.y]) return dist[p1.x][p1.y] - dist[p2.x][p2.y];
					if(p1.x != p2.x)
						return p1.x - p2.x;
					return p1.y - p2.y;
				}
				
			});
			dist[start.x][start.y] = 0;
			qu.add(start);
			int[][] dir = { {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
			while(!qu.isEmpty()) {
				Point p = qu.pollFirst();
				if(p.x == 0 || p.y == 0 || p.x == w-1 || p.y == h-1) {
					System.out.println(dist[p.x][p.y]);
					break;
				}
				for(int i = 0; i < 4; ++i) {
					Point np = new Point(p.x + dir[i][0], p.y + dir[i][1]);
					int new_dist = dist[p.x][p.y] + values[map[np.x][np.y]];
					if(dist[np.x][np.y] > new_dist) {
						qu.remove(np);
						dist[np.x][np.y] = new_dist;
						qu.add(np);
					}
				}
			}
		}
	}

}
