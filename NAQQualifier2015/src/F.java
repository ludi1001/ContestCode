import java.util.*;
public class F {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		while(N-->0) {
			String s = in.nextLine().toLowerCase();
			HashSet<Character> set = new HashSet<Character>();
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c >= 'a' && c <= 'z') {
					set.add(c);
				}
			}
			if (set.size() == 26) {
				System.out.println("pangram");
			} else {
				System.out.print("missing ");
				for (int i = 0; i < 26; ++i) {
					if (!set.contains((char)('a' + i)))
						System.out.print((char)('a' + i));
				}
				System.out.println();
			}
		}
	}

}
