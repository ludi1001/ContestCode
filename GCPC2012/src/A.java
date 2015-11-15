import java.util.*;
public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t > 0) {
			int w = in.nextInt();
			int h = in.nextInt();
			int n = in.nextInt();
			in.nextLine();
			boolean[][][] ship = new boolean[2][h][w];
			int[]left = new int[2];
			for(int k = 0; k < 2; ++k) {
				for(int i = 0; i < h; ++i) {
					String line = in.nextLine();
					for(int j = 0; j < w; ++j)
						if(line.charAt(j) == '#') {
							ship[k][h - i - 1][j] = true;
							++left[k];
						}
				}
			}
			boolean lastTurn = false;
			int result = -1;
			int i = 0;
			int self = 0;
			int other = 1;
			for(; i < n; ++i) {
				int x = in.nextInt();
				int y = in.nextInt();
				if(ship[other][y][x]) {
					//System.out.println(self + " hit " + x + ", " + y);
					ship[other][y][x] = false;
					--left[other];
					if(left[other] <= 0) {
						if(self == 0) {
							lastTurn = true;
							result = 0;
							self = 1; other = 0;
						}
						else if(!lastTurn) {
							result = 1;
							break;
						}
						else
							result = -1;
					}
				}
				else if(lastTurn) break;
				else {
					if(self == 0) {
						self = 1; other = 0;
					}
					else {
						self = 0; other = 1;
					}
				}
			}
			for(++i; i < n; ++i) {
				in.nextInt(); in.nextInt();
			}
			if(result == -1)
				System.out.println("draw");
			else if(result == 0)
				System.out.println("player one wins");
			else if(result == 1)
				System.out.println("player two wins");
			--t;
		}
	}

}
