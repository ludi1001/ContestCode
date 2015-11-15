import java.util.Scanner;
public class A {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int[]good = {1,2,3,3,4,10};
		final int[]evil = {1,2,2,2,3,5,11};
		Scanner in = new Scanner(System.in);
		int battles = in.nextInt();
		for(int i = 1; i <= battles; ++i) {
			int sum = 0;
			for(int j = 0; j < good.length; ++j)
				sum += good[j]*in.nextInt();
			for(int j = 0; j < evil.length; ++j)
				sum -= evil[j]*in.nextInt();
			System.out.print("Battle " + i + ": ");
			if(sum > 0)
				System.out.println("Good triumphs over Evil");
			else if(sum < 0)
				System.out.println("Evil eradicates all trace of Good");
			else
				System.out.println("No victor on this battle field");
		}
	}

}
