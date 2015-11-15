import java.util.*;

public class Navigation {
	
	static int co[] = { -1, 0, 1 };
	static double backtrace(int n, int x, int y, int z, int gx, int gy, int gz, boolean[][][] visited) {
		if (x == gx && y == gy && z == gz) return 0;
		visited[x][y][z] = true;
		
		double min = 100000000;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (x + co[i] < 0 || x + co[i] >= n || y + co[j] < 0 || y + co[j] >= n || z + co[k] < 0 || z + co[k] >= n) continue;
					if ((x + co[i]) % 2 == 1 && (y + co[j]) % 2 == 1 && (z + co[k]) % 2 == 1) continue;
					
					double cost = Math.sqrt(Math.pow((x - (x + co[i])), 2) + Math.pow((y - (y + co[i])), 2) + Math.pow((z - (z + co[i])), 2));
					double next = cost + backtrace(n, x + co[i], y + co[j], z + co[k], gx, gy, gz, visited);
					if (next < min) min = next;
				}
			}
		}
		return min;
	}

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int t = s.nextInt();
		
		for (int i = 0; i < t; i++) {
			int n = s.nextInt();
			
			int sx = s.nextInt();
			int sy = s.nextInt();
			int sz = s.nextInt();
			
			int gx = s.nextInt();
			int gy = s.nextInt();
			int gz = s.nextInt();
			boolean[][][] visit = new boolean[n][n][n];

			System.out.println(backtrace(n, sx, sy, sz, gx, gy, gz, visit));
		}
		
	}

}
