import java.util.*;
import java.awt.Point;
public class H {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t > 0) {
			PriorityQueue<Point> buy = new PriorityQueue<Point>(1, new Comparator<Point>() {
				public int compare(Point a, Point b) {
					return b.x - a.x;
				}
			});
			PriorityQueue<Point> sell = new PriorityQueue<Point>(1, new Comparator<Point>() {
				public int compare(Point a, Point b) {
					return a.x - b.x;
				}
			});
			int n = in.nextInt();
			for(int i = 0; i < n; ++i) {
				String type = in.next();
				int x = in.nextInt();
				in.next();in.next();
				int y = in.nextInt();
				if(type.equals("sell"))
					sell.add(new Point(y,x));
				else
					buy.add(new Point(y,x));
				int price = -1;
				while(!buy.isEmpty() && !sell.isEmpty() && buy.peek().x >= sell.peek().x) {
					int orders = Math.min(buy.peek().y, sell.peek().y);
					price = sell.peek().x;
					buy.peek().y -= orders;
					sell.peek().y -= orders;
					if(buy.peek().y == 0)
						buy.poll();
					if(sell.peek().y == 0)
						sell.poll();
				}
				if(!sell.isEmpty())
					System.out.print(sell.peek().x);
				else
					System.out.print('-');
				System.out.print(' ');
				if(!buy.isEmpty())
					System.out.print(buy.peek().x);
				else
					System.out.print('-');
				System.out.print(' ');
				if(price != -1)
					System.out.print(price);
				else
					System.out.print('-');
				System.out.println();
			}
			--t;
		}
	}

}
