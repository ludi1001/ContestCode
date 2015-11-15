import java.util.*;
public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long cnt1 = in.nextLong();
		long cnt2 = in.nextLong();
		long x = in.nextLong();
		long y = in.nextLong();
		long a = calc(cnt1, x);
		long b = calc(cnt2, y);
		long c = calc(cnt1 + cnt2, x * y);
		System.out.println(Math.max(Math.max(a,b),c));
	}
	public static long calc(long count, long x) {
		System.out.println("start");
		long val = count;
		long lo = 0;
		long num = val/x;
		while(num > 0) {
			long s = (lo/x)*x + x;
			if(lo % x == 0) s -= x;
		    if(s > val) break;
			num = (val - s)/x;
			if(lo < s) ++num;
		    lo = val;
			val += num;
		}
		return val;
	}
}
