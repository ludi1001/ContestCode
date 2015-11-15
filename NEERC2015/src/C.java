import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class C {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		String e = in.nextLine();
		int maxlen = s.length() + e.length();
		String last = "";
		int count = 0;
		for (int i = 2; i <= maxlen; ++i) {
			for (int j = Math.max(1, i - e.length()); j <= Math.min(s.length(), i - 1); ++j) {
				String str = s.substring(0, j) + e.substring(e.length() - (i - j));
				if (!str.equals(last)) {
					++count;
				}
				last = str;
			}
		}
		PrintWriter of = new PrintWriter(new FileWriter(new File("concatenation.out")));
		System.out.println(count);
		of.close();
	}

}
