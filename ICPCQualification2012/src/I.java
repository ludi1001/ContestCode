import java.util.*;

public class I {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ArrayList<String>[] productions = new ArrayList[26];
		HashMap<Character, HashSet<Character>> invProd = new HashMap<Character, HashSet<Character>>();
		boolean[][]possible = new boolean[26][26];
		for(int i = 0; i < 26; ++i)
			productions[i] = new ArrayList<String>();
		String start = null;
		String s;
		while(n > 0) {
			char p = in.next().charAt(0);
			if(start == null)
				start = "" + p;
			in.next(); //arrow
			s = in.nextLine();
			if(s.length() > 0)
				productions[p - 'A'].add(s.substring(1));
			else
				productions[p - 'A'].add("");
			--n;
		}
		//fix null string issues, e.g. A -> BC, B -> , C -> z. z can still follow A
		for(char non = 'A'; non <= 'Z'; ++non) {
			for(String p : productions[non - 'A']) {
				for(int i = 0; i < p.length(); ++i) {
					Character c = p.charAt(i);
					if(!invProd.containsKey(c))
						invProd.put(c, new HashSet<Character>());
					if(!c.equals(non))
						invProd.get(c).add(non);
					if(Character.isLowerCase(c))
						break;
					if(!productions[c - 'A'].contains("")) //if nonterminal can't produce null string, break
						break;
				}
			}
		}
		findTerminals(possible, invProd);
		while(in.hasNextLine()) {
			String[]words = in.nextLine().split(" ");
			String longest = "";
			for(String word : words) {
				for(int startIndex = 0; startIndex < word.length(); ++startIndex) {
					for(int endIndex = word.length(); endIndex > startIndex; --endIndex) {
						if(endIndex - startIndex < longest.length())
							break;
						String test = word.substring(startIndex, endIndex);
						if(tryParse(test, start, productions, possible)) {
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
	public static void findTerminals(boolean[][]possible, HashMap<Character, HashSet<Character>> invProd) {
		//figure out if a given nonterminal A can produce a sequence starting a given terminal T
		//if so, set possible[A][T] = true
		boolean[]visited = new boolean[26];
		Queue<Character> qu = new LinkedList<Character>();
		for(char start = 'a'; start <= 'z'; ++start) {
			if(!invProd.containsKey(start))
				continue;
			for(int i = 0; i < 26; ++i)
				visited[i] = false;
			qu.clear();
			for(char c : invProd.get(start)) {
				visited[c - 'A'] = true;
				qu.offer(c);
			}
			while(!qu.isEmpty()) {
				char c = qu.poll();
				possible[c - 'A'][start - 'a'] = true;
				if(!invProd.containsKey(c))
					continue;
				for(char n : invProd.get(c)) {
					if(!visited[n - 'A']) {
						qu.offer(n);
						visited[n - 'A'] = true;
					}
				}
			}
		}
		/*for(char non = 'A'; non <= 'Z'; ++non) {
			System.out.print(non + ":");
			for(char start = 'a'; start <= 'z'; ++start) {
				if(possible[non - 'A'][start - 'a']) {
					System.out.print(" " + start);
				}
			}
			System.out.println();
		}*/
	}
	public static boolean tryParse(String s, String start, ArrayList<String>[] productions, boolean[][]possible) {
		Queue<String> qu = new LinkedList<String>();
		HashSet<String> visited = new HashSet<String>();
		qu.add(start);
		visited.add(start);
		//System.out.println(s + "%%%");
		while(!qu.isEmpty()) {
			String state = qu.poll();
			//System.out.println(state);
			if(state.equals(s))
				return true;
			int j = 0;
			if(state.length() > 100)
				continue;
			int firstCaps;
			for(firstCaps = 0; firstCaps < state.length(); ++firstCaps) {
				if(Character.isUpperCase(state.charAt(firstCaps)))
					break;
			}
			if(firstCaps == state.length()) //no caps
				continue;
			if(firstCaps > s.length()) //too many leading terminals
				continue;
			boolean works = true;
			if(firstCaps < s.length()) {
				if(!state.substring(0, firstCaps).equals(s.substring(0, firstCaps))) //terminals don't match
					continue;
				char nextChar = s.charAt(firstCaps);
				//System.out.println(nextChar);
				int lastCaps = firstCaps;
				while(true) {
					if(lastCaps >= state.length()) {
						//System.out.println("too long");
						works = false;
						break;
					}
					char sc = state.charAt(lastCaps);
					if(Character.isLowerCase(sc)) {
						if(sc == nextChar) {
							--lastCaps;
							break;
						}
						//System.out.println("is lower");
						works = false;
						break;
					}
					if(possible[sc - 'A'][nextChar - 'a'])
						break;
					if(!productions[sc - 'A'].contains("")) {
						//System.out.println("no null");
						works = false;
						break;
					}
					++lastCaps;
				}
				if(!works)
					continue;
				state = state.substring(0, firstCaps) + state.substring(lastCaps);
			}
			else { //here, we have matched the full string, so either it works or it doesn't
				//System.out.println("full match");
				while(firstCaps < state.length()) {
					char sc = state.charAt(firstCaps);
					if(Character.isLowerCase(sc)) {
						works = false;
						break;
					}
					if(!productions[sc - 'A'].contains("")) {
						works = false;
						break;
					}
					++firstCaps;
				}
				if(!works)
					continue;
			}
			for(int i = j; i < state.length(); ++i) {
				if(Character.isUpperCase(state.charAt(i))) {
					for(String n : productions[state.charAt(i) - 'A']) {
						String newState = state.substring(0, i) + n + state.substring(i + 1);
						if(!visited.contains(newState)) {
							qu.offer(newState);
							visited.add(newState);
						}
					}
					break;
				}
			}
		}
		return false;
	}
}
