import java.util.*;
public class H {
	static class Data {
		String s;
		int x;
		int y;
	}
	static class Node {
		double x0, x1, y0, y1;
		/* Type ranges from 0 to 4 depending on configuration:
		 * 0:        2: 
		 * |---|        ^   |
		 * |   |        |   |
		 * |   v        |---|
		 * 
		 * 1:       3:
		 * <--|        |---
		 *    |        |
		 * ---|        |--->
		 */
		int type;
		List<Data> data = new LinkedList<Data>();
		Node (double x0, double x1, double y0, double y1) {
			this.x0 = x0;
			this.x1 = x1;
			this.y0 = y0;
			this.y1 = y1;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int S = in.nextInt();
		Node root = new Node(0, S, 0, S);
		for (int i = 0; i < n; ++i) {
			Data data = new Data();
			data.x = in.nextInt();
			data.y = in.nextInt();
			data.s = in.next();
			root.data.add(data);
		}
		Stack<Node> qu = new Stack<Node>();
		qu.add(root);
		while (!qu.isEmpty()) {
			Node node = qu.pop();
			if (node.data.size() == 0) continue;
			if (node.data.size() == 1) {
				System.out.println(node.data.get(0).s);
				continue;
			}
			
			double hx = (node.x1 + node.x0) / 2;
			double hy = (node.y1 + node.y0) / 2;
			Node[]nodes = new Node[4];
			nodes[0] = new Node(node.x0, hx, node.y0, hy);
			nodes[1] = new Node(hx, node.x1, node.y0, hy);
			nodes[2] = new Node(hx, node.x1, hy, node.y1);
			nodes[3] = new Node(node.x0, hx, hy, node.y1);
			
			int[][]order = { { 0, 3, 2, 1 }, { 0, 1, 2, 3 }, { 2, 1, 0, 3 }, { 2, 3, 0, 1} };
			int ind = node.type;
			nodes[order[ind][0]].type = node.type/2 + (node.type + 1) % 2;
			nodes[order[ind][1]].type = nodes[order[ind][2]].type = node.type;
			nodes[order[ind][3]].type = (nodes[order[ind][0]].type + 2) % 4;
			
			for (Data data : node.data) {
				if (data.x <= hx) {
					if (data.y <= hy) {
						nodes[0].data.add(data);
					} else {
						nodes[3].data.add(data);
					}
				} else {
					if (data.y <= hy) {
						nodes[1].data.add(data);
					} else {
						nodes[2].data.add(data);
					}
				}
			}
			for (int i = 3; i >= 0; --i) {
				qu.push(nodes[order[ind][i]]);
			}
		}
	}
}
