import java.util.*;
public class ProblemH {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int[][]map = new int[40][40];
		while(true) {
			int w = scanner.nextInt();
			int h = scanner.nextInt();
			int sx = 0, sy = 0;
			int max = 0;
			if(w == 0 && h == 0)
				break;
			for(int i = 0; i < h; ++i) {
				String s = scanner.next();
				for(int j = 0; j < w; ++j) {
					char c = s.charAt(j);
					if(c == 'x' || c == 'X')
						map[i][j] = -1;
					else if(c == ' ')
						map[i][j] = -2;
					else {
						map[i][j] = c - '0';
						if(map[i][j] == 0) {
							sx = i;
							sy = j;
						}
						if(map[i][j] > max)
							max = map[i][j];
					}
				}
			}
			
			Queue<int[]> qu = new LinkedList<int[]>();
			int[]start = new int[5];
			start[0] = sx;
			start[1] = sy;
			start[2] = 0;
			start[3] = 0;
			start[4] = 0;
			while(!qu.isEmpty()) {
				int[]node = Arrays.copyOf(qu.poll(), 5);
				if(node[2] == max)
					break;
			}
		}
	}
}
