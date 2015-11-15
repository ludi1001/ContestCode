import java.util.*;
public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		for(int t = 0; t < testCases; ++t) {
			int n = in.nextInt();
			int[][][]arr = new int[n + 1][2][2];
			arr[0][0][0] = in.nextInt();
			arr[0][1][0] = in.nextInt();
			arr[n][0][0] = in.nextInt();
			arr[n][1][0] = in.nextInt();
			
			for(int i = 1; i < n; ++i) 
				arr[i][0][0] = in.nextInt();
			for(int i = 1; i < n; ++i)
				arr[i][0][1] = in.nextInt();
			for(int i = 1; i < n; ++i)
				arr[i][1][0] = in.nextInt();
			for(int i = 1; i < n; ++i)
				arr[i][1][1] = in.nextInt();
			
			int[]min = new int[2];
			min[0] = arr[0][0][0];
			min[1] = arr[0][1][0];
			for(int i = 1; i < n; ++i) {
				int[]newmin = new int[2];
				newmin[0] = Math.min(min[0] + arr[i][0][1], min[1] + arr[i][1][0]);
				newmin[1] = Math.min(min[1] + arr[i][1][1], min[0] + arr[i][0][0]);
				min = newmin;
			}
			min[0] += arr[n][0][0];
			min[1] += arr[n][1][0];
			System.out.println(Math.min(min[0], min[1]));
		}
	}

}
