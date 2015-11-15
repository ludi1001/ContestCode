import java.util.Scanner;
public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int P = in.nextInt();
		while(P-->0) {
			int count = 0;
			System.out.print(in.nextInt() + " ");
			int prev = in.nextInt();
			for(int i = 1; i < 15; ++i) {
				int cur = in.nextInt();
				if(cur > prev)
					++count;
				prev = cur;
			}
			System.out.println(count);
		}
	}

}
