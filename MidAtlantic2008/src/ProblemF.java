import java.util.Scanner;
public class ProblemF {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			int N = scanner.nextInt();
			int T1 = scanner.nextInt();
			int T2 = scanner.nextInt();
			int T3 = scanner.nextInt();
			if(N + T1 + T2 + T3 == 0)
				break;
			int max = 3*N - 1;
			max += N;
			if(T2 > T1)
				max += T2 - T1;
			else
				max += T2 + N - T1;
			if(T2 > T3) {
				max += T2 - T3;
			}
			else {
				max += T2 + N - T3;
			}
			System.out.println(max);
		}
		scanner.close();
	}
}
