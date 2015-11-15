import java.util.Scanner;
public class D {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		long[][]g = new long[801][801];
		g[400][400] = 1;
		while(N-->0) {
			int vx = in.nextInt();
			int vy = in.nextInt();
			long[][]ng = new long[801][801];
			for (int i = 0; i < 801; ++i) {
				for (int j = 0; j < 801; ++j) {
					if(g[i][j] > 0) {
						ng[i+vx][j+vy] += g[i][j];
					}
				}
			}
			for(int i = 0; i < 801; ++i)for(int j = 0; j < 801; ++j)
				ng[i][j] += g[i][j];
			g = ng;
		}
		System.out.println(g[400][400] - 1);
	}

}