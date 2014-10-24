import java.util.*;
public class ProblemD {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		while(true) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			if(n == 0 && m == 0)
				break;
			arr.clear();
			arr2.clear();
			for(int i = 0; i < n; ++i) {
				arr.add(scanner.nextInt());
			}
			for(int i = 0; i < n - 1; ++i) {
				arr2.add(arr.get(i) * arr.get(i + 1));
			}
			Collections.sort(arr2);
			int sum = 0;
			for(int i = 0; i < n - m; ++i)
				sum += arr2.get(i);
			System.out.println(sum);
		}
		scanner.close();
	}
}
