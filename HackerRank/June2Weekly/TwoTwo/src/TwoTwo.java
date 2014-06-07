/*
Pretty self-evident... use a trie to check if a given string is a power of two.
Precompute the strings beforehand using BigInteger.

Notes: the trie works from the least significant digit. Not entirely sure if this is warranted,
but intuition says that I can get minor constant factor speedup by checking least significant digit first
with the idea being that I can terminate the search for a substring ending with digit j if digit j is odd (i.e. prune the search).
I can't do the same if I check for substrings starting with digit j because the next digit could always make it a power of 2.
*/
import java.util.Scanner;
import java.math.BigInteger;
public class TwoTwo {
	static class Trie {
		boolean end;
		Trie[]children;
		Trie() {
			end = false;
			children = new Trie[10];
		}
		void insert(String val) {
			if(val.length() == 0) {
				end = true;
				return;
			}
			//int index = val.charAt(0) - '0';
			int index = val.charAt(val.length() - 1) - '0';
			if(children[index] == null)
				children[index] = new Trie();
			//children[index].insert(val.substring(1));
			children[index].insert(val.substring(0, val.length() - 1));
		}
	}
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Trie trie = new Trie();
		BigInteger x = BigInteger.ONE;
		trie.insert(x.toString());
		for(int i = 0; i < 800; ++i) {
			x = x.shiftLeft(1);
			trie.insert(x.toString());
		}
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		in.nextLine();
		while(T-- > 0) {
			String s = in.nextLine();
			long count = 0;
			for(int i = 0; i < s.length(); ++i) {
				Trie node = trie.children[s.charAt(i) - '0'];
				if(node == null) continue;
				if(node.end) ++count;
				for(int j = 1; j < 243; ++j) {
					if(i - j < 0) break;
					node = node.children[s.charAt(i - j) - '0'];
					if(node == null) break;
					if(node.end) ++count;
				}
			}
			System.out.println(count);
		}
		System.out.println("time " + (System.currentTimeMillis() - start));
	}
}
