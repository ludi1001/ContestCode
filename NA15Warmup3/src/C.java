import java.util.*;
public class C {
	public static long fact(long n) {
		if (n == 0) return 1;
		long res = 1;
		for (int i = 2; i <= n; ++i)
			res *= i;
		return res;
	}
	public static String p(String s, long K) {
		if (s.length() == 1) return s;
		char[]counts = new char[256];
		for (char c : s.toCharArray()) ++counts[c];
		long num = fact(s.length());
		for (char c = 'A'; c <= 'Z'; ++c) {
			num /= fact(counts[c]);
		}
		long lastm = 0;
		long m = 0;
		for (char c = 'A'; c <= 'Z'; ++c) {
			if (counts[c] <= 0) continue;
			m += num * counts[c] / s.length();
			if (m >= K) {
				int ind = s.indexOf(c);
				String ns = s.substring(0, ind) + s.substring(ind + 1);
				return "" + c + p(ns, K - lastm);
			}
			lastm = m;
		}
		return "bad";
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			String s = in.next();
			long K = in.nextLong();
			if (s.equals("#")) break;
			System.out.println(p(s, K));
		}
	}

}
