import java.util.*;
public class H {
	public static long merge(int[]arr, int[]res, int start, int end) {
		if(start == end || start + 1 == end) {
			res[start] = arr[start];
			return 0;
		}
		int mid = start + (end - start)/2;
		long swaps = merge(arr, res, start, mid) + merge(arr, res, mid, end);
		int li = start;
		int ri = mid;
		for(int i = start; i < end; ++i) {
			if(li < mid && ri < end) {
				if(arr[li] < arr[ri])
					res[i] = arr[li++];
				else {
					res[i] = arr[ri++];
					swaps += mid - li;
				}
			}
			else if(li >= mid)
				res[i] = arr[ri++];
			else
				res[i] = arr[li++];
		}
		for(int i = start; i < end; ++i)
			arr[i] = res[i];
		return swaps;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N;
		while((N = in.nextInt()) != 0) {
			in.nextLine();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			int i = 0;
			for(String s : in.nextLine().split(" ")) {
				map.put(s, i);
				++i;
			}
			int[]arr = new int[i];
			i = 0;
			for(String s : in.nextLine().split(" ")) {
				arr[i] = map.get(s);
				++i;
			}
			int[]res = new int[i];
			System.out.println(merge(arr, res, 0, i));
			//for(int j = 0; j < i; ++j) System.out.print(res[j] + " ");
		}
	}

}
