import java.util.*;


public class I2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		HashMap<String, ArrayList<String>> productions = new HashMap<String, ArrayList<String>>();
		String start = in.next();
		n--;
		in.next();
		in.next(" ");
		String s = in.nextLine();
		productions.put(s,  new ArrayList<String>());
		productions.get(s).add(start);
		while(n > 0) {
			String p = in.next();
			in.next(); //arrow
			in.next(" ");
			s = in.nextLine();
			if(!productions.containsKey(s))
				productions.put(s,  new ArrayList<String>());
			productions.get(s).add(start);
			--n;
		}
		while(in.hasNextLine()) {
			String[]words = in.nextLine().split(" ");
			String longest = "";
			for(String word : words) {
				for(int startIndex = 0; startIndex < word.length() - 1; ++startIndex) {
					for(int endIndex = word.length(); endIndex > startIndex; --endIndex) {
						if(endIndex - startIndex < longest.length())
							break;
						String test = word.substring(startIndex, endIndex);
						if(tryParse(test, start, productions)) {
							if(longest.length() < test.length())
								longest = test;
						}
					}
				}

			}
			if(longest.length() == 0)
				longest = "NONE";
			System.out.println(longest);
		}
	}
	
	public boolean tryParse(String target, String start, HashMap<String, ArrayList<String>> productions) {
		Queue<String>
	}

}
