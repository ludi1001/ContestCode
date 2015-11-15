import java.util.*;
public class ProblemE {
	public static void main(String[]args) {
		int n = 0;
		Scanner scanner = new Scanner(System.in);
		boolean first = true;
		while((n = scanner.nextInt()) != 0) {
			if(first)
				first = false;
			else
				System.out.println();
			int[]tree = new int[(1 << n) - 1];
			for(int i = 0; i < tree.length; ++i)
				tree[i] = scanner.nextInt() - 1;
			int m = scanner.nextInt();
			while(m > 0) {
				int k = scanner.nextInt() - 1;
				//find lost position
				int level = 0;
				int r = 0;
				do {
					r = tree.length - (1 << (n - level)) + 1;
					r += (k >> (level + 1));
					++level;
				} while(r < tree.length && tree[r] == k);
				//System.out.println(k + "::" + level);
				int min = (1 << n) - ((1 << (level - 1)) - 1);
				int max = max(k, tree, n);
				System.out.println("Player " + (k + 1) + " can be ranked as high as " + max + " or as low as " + min +".");
				--m;
			}
		}
	}
	public static int max(int k, int[]tree, int n) {
		int level = 0;
		int r = 0;
		do {
			r = tree.length - (1 << (n - level)) + 1;
			r += (k >> (level + 1));
			++level;
		} while(r < tree.length && tree[r] == k);
		//System.out.println(r);
		if(r >= tree.length)
			return 1;
		else
			return max(tree[r], tree, n) + 1;
	}
}
