import java.util.Scanner;
public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int house = 1;
		while(true) {
			int W = in.nextInt();
			int L = in.nextInt();
			if (W == 0 && L == 0) break;
			in.nextLine();
			char[][]room = new char[W][L];
			int x = 0, y = 0, dir = 0;
			int[][]dirmap = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
			for (int cy = 0; cy < L; ++cy) {
				String row = in.nextLine();
				for (int cx = 0; cx < W; ++cx) {
					room[cx][cy] = row.charAt(cx);
					if (room[cx][cy] == '*') {
						x = cx;
						y = cy;
						if (x == 0) {
							dir = 2;
						} else if(y == 0) {
							dir = 1;
						} else if (x == W - 1) {
							dir = 0;
						} else {
							dir = 3;
						}
					}
				}
			}
			int[][]cmap = { {1, 3}, {0, 2}, {3, 1},{2, 0} };
			x += dirmap[dir][0];
			y += dirmap[dir][1];
			while(x != 0 && y != 0 && x != W - 1 && y != L - 1) {
				if (room[x][y] == '/') {
					dir = cmap[dir][0];
				} else if(room[x][y] == '\\') {
					dir = cmap[dir][1];
				}
				x += dirmap[dir][0];
				y += dirmap[dir][1];
			}
			room[x][y] = '&';
			System.out.println("HOUSE " + house);
			for (int i = 0; i < L; ++i) {
				for (int j = 0; j < W; ++j) {
					System.out.print(room[j][i]);
				}
				System.out.println();
			}
			++house;
		}
	}

}
