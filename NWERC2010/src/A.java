import java.util.*;
public class A {
	static class Pay implements Comparable<Pay> {
		int max;
		int pay;
		int index;
		public Pay(int m, int i) {
			max = m;
			index = i;
		}
		public int compareTo(Pay other) {
			if(other.max != this.max)
				return this.max - other.max;
			return other.index - this.index;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		for(int t = 0; t < cases; ++t) {
			int price = in.nextInt();
			int n = in.nextInt();
			Pay[]arr = new Pay[n];
			for(int i = 0; i < n; ++i)
				arr[i] = new Pay(in.nextInt(), i);
			Arrays.sort(arr);
			int amt = 0;
			for(int i = 0; i < n; ++i) {
				arr[i].max -= amt;
				if(arr[i].max * (n - i) <= price) {
					price -= arr[i].max * (n - i);
					amt += arr[i].max;
					arr[i].pay = amt;
				}
				else {
					amt += price / (n - i);
					arr[i].pay = amt;
					for(int j = i + 1; j < n; ++j)
						arr[j].pay = amt;
					for(int j = 0; j < (price % (n - i)); ++j)
						++arr[n - j - 1].pay;
					price = 0;
					break;
				}
			}
			if(price != 0)
				System.out.println("IMPOSSIBLE");
			else {
				Arrays.sort(arr, new Comparator<Pay>() {
					public int compare(Pay a, Pay b) {
						return a.index - b.index;
					}
				});
				for(int i = 0; i < n - 1; ++i)
					System.out.print(arr[i].pay + " ");
				System.out.println(arr[n - 1].pay);
			}
		}
	}

}
