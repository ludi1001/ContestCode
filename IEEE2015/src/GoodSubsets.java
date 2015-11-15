import java.util.*;
public class GoodSubsets {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt(); 
		while(t-->0) {
			in.nextLine();
			String[]tokens = in.nextLine().split(" ");
			int[]A = new int[tokens.length];
			for(int i = 0; i < tokens.length; ++i)
				A[i] = Integer.parseInt(tokens[i]);
			Arrays.sort(A);
			int k = in.nextInt();
			
			int[]possible = new int[k];
			possible[0] = 1;
			for(int i = 0; i < A.length; ++i) {
				for(int s = k - 1; s >= A[i]; --s) {
					possible[s] += possible[s - A[i]];
				}
				/*for(int j = 0; j < k; ++j) {
					if(possible[j] > 0)
					System.out.print(j + ": " + possible[j] + " ");
				}
				System.out.println();*/
			}
			int count = 0;
			for(int i = 0; i < possible.length; ++i)
				count += possible[i];
			System.out.println(count - 1);
		}
	}
}
