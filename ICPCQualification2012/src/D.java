import java.util.*;
public class D {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int max = in.nextInt();
			int n = in.nextInt();
			
		}
	}
	public static boolean isAP(int[] mks, int max) {
		if (mks.length == 1)
			return true;
		Arrays.sort(mks);
		int diff = mks[1]-mks[0];
		for (int i=1; i < mks.length-1; ++i) {//check bounds
			if (mks[i+1]-mks[i] != diff) {
				return false;
			}
		}
		if (mks[0]-diff >= 0 || mks[mks.length-1]+diff < max) {
			return false;
		}
		return true;
	}
	public static int setcover(int num, ArrayList<Integer> subs) {
		//check there is a cover
		int totalmask = 0;
		for (int anum : subs) {
			totalmask = totalmask & anum;
		}
		if (totalmask != -1+(1 << num)) {
			return -1;
		}
		
		
		Collections.sort(subs, new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				int a = arg0 & (-1+(1 << num));
				int b = arg1 & (-1+(1 << num));
				if (a > b)
					return 1;
				if (b > a)
					return -1;
				return 0;
			}
			
		});
		
		
		
	}
	}
}
