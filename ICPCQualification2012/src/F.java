import java.util.*;
public class F {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][]matrix = new int[9][9];
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		for(int i = 0; i < n; ++i) {
			matrix[in.nextInt()][in.nextInt()] = 1;
		}
		int count = 0;
		for(int i = 0; i < m - 1; ++i) {
			count += dfs(matrix, i, i, new HashSet<Integer>());
		}
		System.out.println(count);
	}
	public static int dfs(int[][]matrix, int start, int cur, Set<Integer> visited) {
		int count = 0;
		if(visited.contains(cur))
			return 0;
		for(int i = start; i < matrix.length; ++i) {
			if(matrix[cur][i] == 1) {
				if(i == start)
					++count;
				else {
					Set<Integer> newVisited = new HashSet<Integer>(visited);
					newVisited.add(cur);
					count += dfs(matrix, start, i, newVisited);
				}
			}
		}
		return count;
	}
}
