import java.util.*;
public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 0; i < m; ++i)
			arr.add(in.nextInt());
		int fedor = in.nextInt();
		int count = 0;
		for(int x : arr)
			if(Integer.bitCount(x ^ fedor) <= k)
				++count;
		System.out.println(count);
	}

}
