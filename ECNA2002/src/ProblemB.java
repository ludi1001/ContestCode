import java.util.*;
public class ProblemB {
	public static void main(String[]args) {
		final int[][]dirs = { {1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1} };
		Scanner scanner = new Scanner(System.in);
		int sets = scanner.nextInt();
		while(sets > 0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			int k = scanner.nextInt();
			int l = scanner.nextInt();
			HashSet<Integer> empire = new HashSet<Integer>();
			HashSet<Integer> visited = new HashSet<Integer>();
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < m; ++j)
					for(int p = 0; p < k; ++p)
						empire.add(i + j*n + p*m*n);
			int months = 0;
			while(l > 0) {
				int p = scanner.nextInt();
				while(p > 0) {
					int blah = scanner.nextInt();
					if(empire.contains(blah))
						empire.remove(blah);
					p--;
				}
				if(empire.size() > 0) {
					//bfs
					visited.clear();
					Queue<Integer> qu = new LinkedList<Integer>();
					int start = empire.iterator().next();
					qu.add(start);
					visited.add(start);
					while(!qu.isEmpty()) {
						int loc = qu.poll();
						int x = loc % n;
						loc /= n;
						int y = loc % m;
						loc /= m;
						int z = loc; 
						for(int i = 0; i < 6; ++i) {
							int nx = x + dirs[i][0];
							int ny = y + dirs[i][1];
							int nz = z + dirs[i][2];
							if(nx < 0 || ny < 0 || nz < 0)
								continue;
							if(nx >= n || ny >= m || nz >= k)
								continue;
							int nloc = nx + ny*n + nz*n*m;
							if(!visited.contains(nloc) && empire.contains(nloc)) {
								visited.add(nloc);
								qu.add(nloc);
							}
						}
					}
					if(visited.size() != empire.size())
						months++;
				}
				l--;
			}
			System.out.println(months);
			sets--;
		}
	}
}
