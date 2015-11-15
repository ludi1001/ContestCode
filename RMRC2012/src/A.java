import java.util.Scanner;
public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = 1;
		while(true) {
			int L = in.nextInt();
			int P = in.nextInt();
			int V = in.nextInt();
			if (L == 0 && P == 0 && V == 0) break;
			int ans = L*(V/P) + Math.min(L, V%P);
			System.out.println("Case " + t + ": " + ans);
		}
	}

}
