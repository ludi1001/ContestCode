import java.util.*;
public class E {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashMap<Integer, LinkedList<Integer>> map = new HashMap<Integer, LinkedList<Integer>>();
		int[]dp = new int[n+1];
		int[]point = new int[n+1];
		int[][]store = new int[n+1][3];
		int[]nums = new int[n+1];
		for(int i = 1; i <= n; ++i) {
			int num = in.nextInt();
			nums[i] = num;
			dp[i] = dp[i-1];
			point[i] = i-1;
			//find closest quadruple
			if(!map.containsKey(num)) map.put(num, new LinkedList<Integer>());
			else {
				int m = -1;
				
				for(int p : map.get(num)) {
					for(int j = p-1; j > 0; --j) {
						if(map.containsKey(nums[j]) && map.get(nums[j]).peekLast() > p) {
							m = Math.max(m, j);
							break;
						}
					}
				}
				
				if(m > 0 && dp[m-1] + 1 > dp[i]) {
					dp[i] = dp[m-1] + 1;
					store[i][0] = 1;
					store[i][1] = nums[m];
					store[i][2] = num;
					point[i] = m-1;
				}
				//System.out.println(i + " " + num + " " + m + " " + map.get(num));
			}
			map.get(num).add(i);
		}
		System.out.println(4*dp[n]);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int p = n;
		while(p != 0) {
			if(store[p][0] == 1) {
				arr.add(store[p][2]);
				arr.add(store[p][1]);
				arr.add(store[p][2]);
				arr.add(store[p][1]);
			}
			p = point[p];
		}
		Collections.reverse(arr);
		if(arr.size() > 0) {
			System.out.print(arr.get(0));
			for(int i = 1; i < arr.size(); ++i)
				System.out.print(" " + arr.get(i));
		}
	}

}
