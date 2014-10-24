import java.util.*;
public class L {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String vowels = "aiyeou";
		String consts = "bkxznhdcwgpvjqtsrlmf";
		char[]map = new char[128];
		for(int i = 0; i < 128; ++i)
			map[i] = (char)i;
		for(int i = 0; i < 3; ++i) {
			map[vowels.charAt(i)] = vowels.charAt(i+3);
			map[vowels.charAt(i)-'a'+'A'] = (char) (vowels.charAt(i+3)-'a'+'A');
			map[vowels.charAt(i+3)] = vowels.charAt(i);
			map[vowels.charAt(i+3)-'a'+'A'] = (char) (vowels.charAt(i)-'a'+'A');
		}
		for(int i = 0; i < 10; ++i) {
			map[consts.charAt(i)] = consts.charAt(i + 10);
			map[consts.charAt(i + 10)] = consts.charAt(i);
			map[consts.charAt(i)-'a'+'A'] = (char) (consts.charAt(i + 10)-'a'+'A');
			map[consts.charAt(i + 10)-'a'+'A'] = (char) (consts.charAt(i)-'a'+'A');
		}
		while(in.hasNextLine()) {
			String s = in.nextLine();
			String o = "";
			for(char c : s.toCharArray())
				o += map[c];
			System.out.println(o);
		}
	}

}
