import java.util.*;
public class ProblemA {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int n;
		HashSet<Integer> passed = new HashSet<Integer>();
		boolean first = true;
		while((n = scanner.nextInt()) > 0) {
			if(first)
				first = false;
			else
				System.out.println();
			passed.clear();
			int k = scanner.nextInt() - 1;
			if(k < 0)
				k = n - 1;
			boolean[]dir = new boolean[n];
			for(int i = 0; i < n; ++i)
				if(scanner.next().charAt(0) == 'L')
					dir[i] = true;
				else
					dir[i] = false;
			for(int i = 0; i < n; ++i)
				passed.add(i);
			int turns = 0;
			int last = 0;
			int newK = 0;
			while(!passed.isEmpty()) {
				++turns;
				//System.out.println("passing from " + last + " to " + k);
				passed.remove(k);
				if(dir[k]) {
					//if(last - k == 1 || (k == n - 1 && last == 0)) {
					//	newK = k - 1;
					//}
					//else
						newK = last - 1;
				}
				else {

					//if(k - last == 1 || (k == 0 && last == n - 1)) {
					//	newK = k + 1;
					//}
					//else
						newK = last + 1;
				}
				if(newK < 0)
					newK = n - 1;
				else if(newK >= n)
					newK = 0;
				if(newK == k) {
					if(dir[k])
						newK = k - 1;
					else
						newK = k + 1;
				}
				if(newK < 0)
					newK = n - 1;
				else if(newK >= n)
					newK = 0;
				dir[k] = !dir[k];
				//for(int i = 0 ; i < n; ++i)
				//	System.out.print((dir[i] ? "L" : "R") + " ");
				//System.out.println();
				last = k;
				k = newK;
			}
			int m = last + 1;
			if(m > n)
				m = 1;
			System.out.print("Classmate " + m + " got the ball last after " + turns + " tosses.");
		}
		scanner.close();
	}
}
