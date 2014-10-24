import java.util.Scanner;
public class B {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-->0) {
			System.out.print(in.nextInt() + " ");
			double D = in.nextDouble();
			double A = in.nextDouble();
			double B = in.nextDouble();
			double F = in.nextDouble();
			System.out.printf("%.2f\n", D/(A+B)*F);
		}
	}
}
