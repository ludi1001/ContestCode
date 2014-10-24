import java.util.*;
public class C {
	public static void main(String[]args) throws Exception {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt();
			int[]arr = new int[n];
			for(int i = 0; i < n; ++i)
				arr[i] = in.nextInt();
			Arrays.sort(arr);
			System.out.println(2*(arr[n-1] - arr[0]));
		}
	}
}
