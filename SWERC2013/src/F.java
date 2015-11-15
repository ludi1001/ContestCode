import java.util.*;
public class F {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		
		/*define d[x][0] as the solution to the problem for 5^x - 1
		 define d[x][1] as 5^x - d[x][0]
		In other words, d[x][0] is the number even ones that work for all k < 5^x
		and d[x][0] is the number of odd ones.
		The idea is to precalculate d array and then convert n into base-5 and use the d's to calculate the final value.
		*/ 
		long[][]d = new long[27][2];
		d[0][0] = 1;
		d[0][1] = 0;
		long x = 5;
		for(int i = 1; i < d.length; ++i) {
			if(i % 2 == 0)
				d[i][0] = 3*d[i-1][0] + 2*d[i-1][1];
			else
				d[i][0] = 5*d[i-1][0];
			d[i][1] = x - d[i][0];
			x *= 5;
		}
		while(true) {
			long n = in.nextLong();
			if(n == -1) break;
			long sum = 0;
			//The following for-loop was added adhoc to get rid of a off-by-one error:
			//It essentially calculates whether n! has an even number of trailing zeros
			//The number of trailing zeros is simply floor(n/5) + floor(n/25) + floor(n/125) + ...
			//There's a better to do this...
			long t = 0;
			long y = 5;
			while(y <= n) {
				t += n / y;
				y *= 5;
			}
			//We have to process the digits from MSB to LSB, ergo, grab them all first...
			ArrayList<Integer> digits = new ArrayList<Integer>();
			while(n != 0) {
				digits.add((int)(n % 5));
				 n /= 5;
			}
			boolean not = false;
			//and iterate in reverse...
			for(int i = digits.size()-1; i >= 0; --i) {
				int digit = digits.get(i);
				/* so the not variable decides whether the correct section should switch the odd/even counts
				 * As an example, consider 32 (=112_5)
				 * The proper count for 32 is 1*15 + 1*5 + 2*0
				 * Note that the reason the 2 contributes 0 is that the presence of the 5 digit "inverts" the values
				 */
				if(!not) {
					if(i % 2 == 0)
						sum += digit * d[i][0];
					else
						sum += digit/2 * d[i][1] + (digit - digit/2) * d[i][0];
				}
				else {
					if(i % 2 == 0)
						sum += digit * d[i][1];
					else
						sum += digit/2 * d[i][0] + (digit - digit/2) * d[i][1];
				}
				if(i % 2 == 1 && digit % 2 == 1) {
					not = !not;
				}
			}

			if(t % 2 == 0)
				++sum;
			System.out.println(sum);
		}
	}
}

