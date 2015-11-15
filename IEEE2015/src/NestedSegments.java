import java.util.Scanner;
import java.util.*;


public class NestedSegments {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		in.nextLine();
		for(int tc = 1; tc <= t; ++tc) {
			TreeSet<Node> nodes = new TreeSet<>();
			String[] nums =  in.nextLine().split(" ");
			ArrayList<Node> byRange = new ArrayList<>();
			for(int i = 0; i < nums.length-1; i+=2) {
				Node node = new Node(Integer.parseInt(nums[i]), Integer.parseInt(nums[i+1]));
				byRange.add(node);
			}
			Collections.sort(byRange, new Comparator<Node>() {

				@Override
				public int compare(Node arg0, Node arg1) {
					int range0 = arg0.high - arg0.low;
					int range1 = arg1.high - arg1.low;
					if(range1 != range0) {
						return range1 - range0;
					}
					return arg0.low - arg1.low;
				}
				
			});
			
			Node root = new Node(-1, 101);
			nodes.add(root);
			int maxDepth = 0;
			for(int i = 0; i < byRange.size(); i++) {
				Node curr = byRange.get(i);
				Iterator<Node> iter = nodes.iterator();
				while(iter.hasNext()) {
					Node par = iter.next();
					if(par.high == curr.high && par.low == curr.low) {
						par.depth++;
						break;
					}
					if(within(par, curr)) {
						curr.parent = par;
						curr.depth = par.depth +1;
						if(curr.depth > maxDepth) maxDepth = curr.depth;
						nodes.add(curr);
						break;
					}
				}
			}
			System.out.println(maxDepth);
			
		}
	}
	
	public static boolean within(Node par, Node ch) {
		return (par.high >= ch.high) && (par.low <= ch.low);	
	}
	
	public static class Node implements Comparable<Node> {
		public int high;
		public int low;
		public int depth;
		public Node parent;
		
		public Node(int low, int high) {
			this.low = low;
			this.high = high;
			this.depth = 0;
			this.parent = null;
		}
		
		public String toString() {
			return low+ " " + high + " " + depth;
		}
		
		@Override
		public int compareTo(Node arg0) {
			if(depth != arg0.depth) {
				return arg0.depth - depth;
			} else {
				if(low != arg0.low)
					return low - arg0.low;
				return high - arg0.high;
			}
			
		}
		
		
	}
}
