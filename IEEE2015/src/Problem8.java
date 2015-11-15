import java.math.BigInteger;
import java.util.*;
public class Problem8 {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		
		BigInteger b1 = s.nextBigInteger();
		BigInteger b2 = s.nextBigInteger();
		
		int n = Math.max(b1.bitLength(), b2.bitLength());
		System.out.println(n);
		int best = 0;
		for (int j = 0; j < n; j++) {
			int cost = 0;
			for (int i = 0; i < n; i++) {
				System.out.println(b1.testBit(i));
				if (cost < 0) cost = 0;
				if (b1.testBit(i) == b1.testBit(i)) cost -= 1;
				else cost += 1;
			
				best = Math.max(cost, best);
			}
			if (b1.bitLength() < b2.bitLength()) b1.shiftLeft(1);
			else b2.shiftLeft(1);
		}
		
		System.out.println(best);
	}
}
