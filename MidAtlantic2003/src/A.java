import java.util.*;
public class A {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int teams = in.nextInt();
		int[]score = new int[teams];
		int[]solved = new int[teams];
		String[]names = new String[teams];
		for(int i = 0; i < teams; ++i) {
			names[i] = in.next();
			for(int j = 0; j < 4; ++j) {
				int sub = in.nextInt();
				int time = in.nextInt();
				if(time > 0) {
					++solved[i];
					score[i] += time + (sub - 1) * 20;
				}
			}
		}
		int bestSolved = solved[0];
		int bestScore = score[0];
		String bestName = names[0];
		for(int i = 1; i < teams; ++i) {
			if(solved[i] > bestSolved) {
				bestSolved = solved[i];
				bestScore = score[i];
				bestName = names[i];
			}
			else if(solved[i] == bestSolved) {
				if(bestScore > score[i]) {
					bestScore = score[i];
					bestName = names[i];
				}
			}
		}
		System.out.println(bestName + " " + bestSolved + " " + bestScore);
	}

}
