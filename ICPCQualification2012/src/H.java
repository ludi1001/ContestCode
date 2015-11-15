import java.awt.Point;
import java.util.*;

public class H {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		if(n == 0) {
			System.out.println("-");
			return;
		}
		int[][]points = new int[n + 2][2];
		//ArrayList<Point> points = new ArrayList<Point>();
		for(int i = 0; i < n; ++i) {
			points[i][0] = in.nextInt(); //new Point(in.nextInt(), in.nextInt());
			points[i][1] = in.nextInt();
		}
		int sx = in.nextInt();
		int sy = in.nextInt();
		int ex = in.nextInt();
		int ey = in.nextInt();
		points[n][0] = sx; //n
		points[n][1] = sy;
		points[n + 1][0] = ex; //n + 1
		points[n + 1][1] = ey;
		final long[]dist = new long[n+2];
		final int[]prev = new int[n+2];
		for(int i = 0; i < n + 1; ++i) {
			dist[i] = -1L;
			prev[i] = -1;
		}
		TreeSet<Integer> qu = new TreeSet<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				int a = arg0;
				int b = arg1;
				if(dist[a] > dist[b])
					return 1;
				if(dist[a] < dist[b])
					return -1;
				return arg0 - arg1;
			}
			
		});
		//n + 1 == start
		//n == end
		qu.add(n + 1);
		dist[n + 1] = 0;
		while(!qu.isEmpty()) {
			int cur = qu.pollFirst();
			if(cur == n)
				break;
			//System.out.println("@" + cur);
			for(int i = 0; i < n + 1; ++i) {
				if(i == cur)
					continue;
				long dx = points[cur][0] - points[i][0];
				long dy = points[cur][1] - points[i][1];
				 long ndist = dx * dx + dy * dy;
				// System.out.println("checking " + i + " " + (ndist + dist[cur]) + "?" + dist[i]);
				 if(dist[i] == -1 || dist[cur] + ndist < dist[i]) {
					 qu.remove(i);
					 dist[i] = dist[cur] + ndist;
					 prev[i] = cur;
					 qu.add(i);
					 //System.out.println("pushing " + i + " " + dist[i]); 
				 }
			}
		}
		/*Stack<Integer> stack = new Stack<Integer>();
		int j = prev[n];
		while(j != n + 1) {
			stack.push(j);
			j = prev[j];
		}
		if(stack.isEmpty())
			System.out.println("-");
		else
			while(!stack.isEmpty()) {
				int i = stack.pop();
				System.out.println(i);
			}
		System.out.flush();*/
		if(prev[n] == n + 1)
			System.out.println("-");
		else {
			int j = prev[n];
			while(j != n + 1) {
				System.out.println(j);
				j = prev[j];
			}
		}
	}

}
