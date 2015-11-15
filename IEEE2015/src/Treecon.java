import java.util.*;
public class Treecon {

	
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int bfs[] = new int[n];
		for (int i = 0; i < n; i++) {
			bfs[i] = s.nextInt();
		}
		int dfs[] = new int[n];
		for (int i = 0; i < n; i++) {
			dfs[i] = s.nextInt();
		}
		
		int temp = 0;
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<Integer>());
		}
		boolean[] done = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			if (temp > n) break;
			temp += 2 << i;
			list.get(bfs[i]).add(bfs[i * 2 + 1]);
			list.get(bfs[i]).add(bfs[i * 2 + 2]);
			done[bfs[i]] = done[bfs[i * 2 + 1]] = done[bfs[i * 2 + 2]] = true;
		}
		for (int i = 1; i < n; i++) {
			if (!done[dfs[i]]) {
				list.get(dfs[i - 1]).add(dfs[i]);
			}
		}
		for (int i = 1; i <= n; i++) {
			System.out.printf("%d:", i);
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.printf(" %d", list.get(i).get(j));
			}
			System.out.printf("\n");
		}
	}
}
