import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class C2 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("concatenation.in"));
		String s = in.nextLine();
		String e = in.nextLine();
		int[]sc = new int[256];
		int[]ec = new int[256];
		for (char c : s.toCharArray()) ++sc[c];
		for (char c : e.toCharArray()) ++ec[c];
		--sc[s.charAt(0)]; --ec[e.charAt(e.length()-1)];
		long count = s.length() * e.length();
		for (int i = 0; i < 256; ++i)
			count -= ((long)sc[i]) * ec[i];
		PrintWriter of = new PrintWriter(new FileWriter(new File("concatenation.out")));
		of.println(count);
		//System.out.println(count);
		of.close();
	}
}
