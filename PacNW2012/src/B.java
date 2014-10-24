import java.util.Scanner;
public class B {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		boolean[]marked = new boolean[10];
		while(in.hasNextInt()) {
			int n = in.nextInt();
			long k = 1;
			for(int j = 0; j < 10; ++j) marked[j] = false;
			boolean live = true;
			while(live) {
				live = false;
				long p = k*n;
				while(p > 0) {
					marked[(int)(p % 10)] = true;
					p /= 10;
				}
				for(int j = 0; j < 10; ++j) 
					if(!marked[j]) {
						live = true;
						break;
					}
				++k;
			}
			System.out.println(k-1);
		}
	}
}
