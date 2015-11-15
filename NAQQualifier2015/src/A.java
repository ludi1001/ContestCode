import java.util.Scanner;
public class A {

	public static int digit(char c) {
		if (c >= '0' && c <= '9') return c - '0';
		else return c - 'a' + 10;
	}
	public static boolean valid(String token, int base) {
		for (int i = 0; i < token.length(); ++i) {
			int d = digit(token.charAt(i));
			if (base == 1) {
				if (d != 1)
					return false;
			}
			else if (d >= base) return false;
		}
		return true;
	}
	public static long convert(String token, int base) {
		if (base == 1) {
			return token.length();
		}
		long num = 0;
		int factor = 1;
		for (int i = token.length() - 1; i >= 0; --i) {
			num += digit(token.charAt(i)) * factor;
			factor *= base;
		}
		return num;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		for (int i = 0; i < N; ++i) {
			String[]tokens = in.nextLine().split(" ");
			boolean worksany = false;
			for (int base = 1; base <= 36; ++base) {
				if (!valid(tokens[0], base) || !valid(tokens[2], base) || !valid(tokens[4], base)) continue;
				long a = convert(tokens[0], base);
				long b = convert(tokens[2], base);
				long c = convert(tokens[4], base);
				boolean works = false;
				if (tokens[1].equals("+")) {
					works = (a + b) == c;
				} else if(tokens[1].equals("-")) {
					works = (a - b) == c;
				} else if(tokens[1].equals("*")) {
					works = (a * b) == c;
				} else if(tokens[1].equals("/")) {
					works = a == b * c;
				}
				if(works) {
					worksany = true;
					if(base < 10) System.out.print(base);
					else if(base == 36) System.out.print('0');
					else System.out.print((char)(base + 'a' - 10));
				}
			}
			if (!worksany) System.out.print("invalid");
			System.out.println();
		}
	}

}
