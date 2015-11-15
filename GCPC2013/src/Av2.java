import java.util.*;
public class Av2 {
	static class Trie {
		Trie[]next;
		boolean isWord = false;
		Trie() {
			next = new Trie[26];
		}
		void addWord(String w) {
			if(w.length() == 0) {
				isWord = true;
				return;
			}
			int c = w.charAt(0) - 'A';
			if(next[c] == null)
				next[c] = new Trie();
			next[c].addWord(w.substring(1));
		}
		/*boolean isWord(String w) {
			if(w.length() == 0)
				return isWord;
			int c = w.charAt(0) - 'A';
			if(next[c] == null)
				return false;
			return next[c].isWord(w.substring(1));
		}*/
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Trie dict = new Trie();
		int w = in.nextInt(); in.nextLine();
		while(w-->0)
			dict.addWord(in.nextLine());
		
		int b = in.nextInt();
		while(b-->0) {
			in.nextLine();
			char[][]board = new char[4][4];
			for(int i = 0; i < 4; ++i) {
				String line = in.nextLine();
				for(int j = 0; j < 4; ++j)
					board[i][j] = line.charAt(j);
			}
			Set<String> found = new HashSet<String>();
			StringBuilder word = new StringBuilder();
			for(int i = 0; i < 4; ++i)
				for(int j = 0; j < 4; ++j)
					doBoggle(board, dict, i, j, found, word);
			
			String longestWord = "";
			int score = 0;
			for(String w1 : found) {
				int len = w1.length();
				if(len == 3 || len == 4) score += 1;
				else if(len == 5) score += 2;
				else if(len == 6) score += 3;
				else if(len == 7) score += 5;
				else if(len >= 8) score += 11;
				
				if(len > longestWord.length()) {
					longestWord = w1;
				}
				else if(len == longestWord.length()) {
					if(w1.compareTo(longestWord) < 0)
						longestWord = w1;
				}
			}
			System.out.println(score + " " + longestWord + " " + found.size());
		}
	}
	public static void doBoggle(char[][]board, Trie trie, int r, int c, Set<String> found, StringBuilder word) {
		int[][]d = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };
		if(r < 0 || c < 0 || r >= 4 || c >= 4)
			return;
		if(trie.next[board[r][c] - 'A'] == null)
			return;
		trie = trie.next[board[r][c] - 'A'];
		
		word.append(board[r][c]);
		if(trie.isWord)
			found.add(word.toString());
		
		for(int i = 0; i < d.length; ++i)
			doBoggle(board, trie, r + d[i][0], c + d[i][1], found, word);
		
		word.deleteCharAt(word.length() - 1);
	}
}
