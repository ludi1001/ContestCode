import java.util.*;
public class Key {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt(); in.nextLine();
		while(k-->0) {
			String[]tokens = in.nextLine().split(" ");
			int w = tokens[0].length();
			boolean[]used = new boolean[tokens.length];
			StringBuilder s = new StringBuilder();
			Arrays.sort(tokens);
			for(int i = 0; i < tokens.length; ++i) {
				used[i] = true;
				s.append(tokens[i]);
				if(recurse(tokens, used, s, w, 1)) continue;
				s = new StringBuilder();
				used[i] = false;
			}
		}
	}
	public static boolean recurse(String[]tokens, boolean[]used, StringBuilder s, int w, int numUsed) {
		if(numUsed == tokens.length) {
			System.out.println(s);
			return true;
		}
		String target = s.substring(s.length() - w  + 1);
		int ind = Arrays.binarySearch(tokens, target);
		ind = -ind-1;
		while(ind < tokens.length && tokens[ind].substring(0, w-1).equals(target)) {
			if(used[ind]) {
				++ind;
				continue;
			}
			used[ind] = true;
			s.append(tokens[ind].charAt(w-1));
			if(recurse(tokens, used, s, w, numUsed + 1)) return true;
			s.deleteCharAt(s.length() - 1);
			used[ind] = false;
			++ind;
		}
		return false;
	}
}
