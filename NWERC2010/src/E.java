import java.util.*;
public class E {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcases = in.nextInt();
		while(testcases > 0) {
			int n = in.nextInt();
			boolean[][]mat = new boolean[n][n];
			int[]arr = new int[n];
			for(int i = 0; i < n; ++i) {
				arr[i] = in.nextInt() - 1;
				for(int j = 0; j < i; ++j)
					mat[arr[j]][arr[i]] = true;
			}
			int m = in.nextInt();
			for(int i = 0; i < m; ++i) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				if(mat[a][b]) {
					mat[a][b] = false;
					mat[b][a] = true;
				}
				else {
					mat[a][b] = true;
					mat[b][a] = false;
				}
			}
			int[]res = topo(mat);
			if(res.length == 0)
				System.out.println("IMPOSSIBLE");
			else {
				for(int i = 0; i < res.length - 1; ++i)
					System.out.print((res[i] + 1) + " ");
				System.out.print(res[res.length - 1] + 1);
				System.out.println();
			}
			--testcases;
		}
	}
	static int[] topo(boolean[][]in) {
		int[]rev = new int[in.length], out = new int[in.length];
		for(int i = 0; i < in.length; ++i)
			for(int j = 0; j < in.length; ++j)
				if(in[i][j]) ++rev[j];
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0; i < in.length; ++i) if(rev[i] == 0)q.add(i);
		if(q.isEmpty())return new int[0];
		int count = 0;
		while(!q.isEmpty()) {
			int t = q.poll();
			for(int i = 0; i < in.length; ++i)
				if(in[t][i]) {
					--rev[i];
					if(rev[i]==0)q.add(i);
				}
			out[count++] = t;
		}
		if(count<out.length)return new int[0];
		return out;
	}
}
