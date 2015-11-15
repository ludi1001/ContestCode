import java.util.*;
public class L {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		HashMap<String, String> map = new HashMap<String, String>();
		while(N-->0) {
			String[]tokens = in.nextLine().split(" ");
			for(int i = 1; i < tokens.length; ++i)
				map.put(tokens[i].toLowerCase(), tokens[0]);
		}
		in.nextLine();
		String punct = ",.!;?()";
		while(in.hasNextLine()) {
			String line = in.nextLine().toLowerCase();
			for(int i = 0; i < punct.length(); ++i)
				line = line.replace(punct.charAt(i), ' ');
			String[]tokens = line.split(" ");
			for(String t : tokens) {
				if(map.containsKey(t)) {
					System.out.println(map.get(t));
					break;
				}
			}
		}
	}
}
