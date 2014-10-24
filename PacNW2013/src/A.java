import java.util.Scanner;
public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-- > 0) {
			int N = in.nextInt();
			int D = in.nextInt();
			int count = 0;
			while(N-- > 0) {
				int v = in.nextInt();
				int f = in.nextInt();
				int c = in.nextInt();
				if(D*c <= f*v)
					++count;
			}
			System.out.println(count);
		}
	}

}
