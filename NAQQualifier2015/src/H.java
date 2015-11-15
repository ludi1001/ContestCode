import java.util.Scanner;
public class H {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		while(N-->0) {
			String s = in.nextLine();
			int K = 0;
			for(int i = 1; i <= 10000; ++i) {
				if (i * i >= s.length()) {
					K = i;
					break;
				}
			}
			for (int j = 0; j < K; ++j) {
				for (int i = K - 1; i >= 0; --i) {
					int ind = i * K + j;
					if (ind >= s.length()) continue;
					System.out.print(s.charAt(ind));
				}
			}
			System.out.println();
		}
	}

}
