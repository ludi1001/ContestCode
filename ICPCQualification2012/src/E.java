import java.math.BigInteger;
import java.util.Scanner;

public class E {

	/**
	 * @param args
	 */
	final static BigInteger zero = new BigInteger("0");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		BigInteger max = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		while(in.hasNext()) {
			int numberOfCases = in.nextInt();
			BigInteger product = new BigInteger("1");
			BigInteger[] cards = new BigInteger[numberOfCases];
 			for (int i1 = 0 ; i1 < numberOfCases; i1 ++){
 				cards[i1] = new BigInteger(in.next());
				product = product.multiply(cards[i1]);
			}
 			max = new BigInteger("0");
			for(int i = 0; i < numberOfCases; ++i) {
				BigInteger everythingBut = product.divide(cards[i]);
				if (cards[i].compareTo(max) > 0 && gcd(cards[i],everythingBut).compareTo(one) == 0){
					max = cards[i];
				}
			}
			System.out.println(max);
		}
		in.close();

	}
	public static BigInteger gcd(BigInteger a, BigInteger b) {
		while(b.compareTo(zero) != 0) {
			BigInteger t = a.mod(b);
			a = b;
			b = t;
		}
		return a;
	}
}
