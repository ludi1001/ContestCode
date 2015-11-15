import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class E {

	public static String[]tokenize(String s) {
		int count = 1;
		for (char c : s.toCharArray()) {
			if (c == '-' || c == '+') ++count;
		}
		String[]t = new String[count];
		t[0] = "";
		int ind = 0;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c == '-' || c == '+') {
				++ind;
				t[ind] = "";
			}
			t[ind] += c;
		}
		return t;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("easy.in"));
		String[]t = tokenize(in.nextLine());
		String out = "";
		for (int i = 0; i < t.length; ++i) {
			if (t[i].length() == 0) continue;
			else if (t[i].charAt(0) == '-') {
				out += t[i].substring(0, 2);
				if (t[i].length() == 2) continue;
				
				int j = 2;
				while(j < t[i].length() && t[i].charAt(j) == '0') {
					++j;
					out += "+0";
				}
				if (j < t[i].length()) {
					out += '+';
					out += t[i].substring(j);
				}
			}
			else {
				out += t[i];
			}
		}
		PrintWriter of = new PrintWriter(new FileWriter(new File("easy.out")));
		of.write(out);
		of.close();
	}

}
