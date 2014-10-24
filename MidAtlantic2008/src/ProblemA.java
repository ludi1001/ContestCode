import java.util.Scanner;
public class ProblemA {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int[]grams = new int[4];
		int[]weights = {0, 9, 4, 4};
		while(true) {
			boolean nonzero = false;
			for(int i = 0; i < 4; ++i) {
				grams[i] = scanner.nextInt() * 10;
				if(grams[i]  > 0)
					nonzero = true;
			}
			if(!nonzero)
				break;
			
			int minSum = 0;
			int maxSum = 0;
			for(int i = 1; i < 4; ++i) {
				if(grams[i] > 0) {
					minSum += (grams[i] - 5) * weights[i];
				}
				maxSum += (grams[i] + 5) * weights[i];
			}
			if(grams[0] >= minSum && grams[0] <=maxSum)
				System.out.println("yes");
			else
				System.out.println("no");
		}
		scanner.close();
	}
}
