import java.util.Scanner;
public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int ab = Math.max(a+b, a*b);
		int bc = Math.max(b+c, b*c);
		int abc = Math.max(Math.max(ab+c,ab*c),Math.max(a+bc,a*bc));
		System.out.println(abc);
	}
}