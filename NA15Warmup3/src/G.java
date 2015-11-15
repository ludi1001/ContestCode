import java.util.Scanner;
public class G {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int rot = in.nextInt();
			if(rot == 0)break;
			String s = in.next();
			for (int i = s.length() - 1; i >= 0; --i) {
				char c = s.charAt(i);
				int ci;
				if (c == '_') ci = 26;
				else if (c == '.') ci = 27;
				else ci = c - 'A';
				ci += rot;
				ci %= 28;
				if (ci == 26) c = '_';
				else if (ci == 27) c = '.';
				else c = (char)(ci + 'A');
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
