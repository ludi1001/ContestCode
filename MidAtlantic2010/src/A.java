import java.util.*;
public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			String line = in.nextLine();
			if(line.equals("0"))
				break;
			int i = 0;
			for(i = line.length()/2 - 1; i >= 0; --i)
				if(line.charAt(i) < line.charAt(line.length() - 1 - i))
					break;
			if(i == 0 && line.charAt(0) == line.charAt(line.length() - 1))
				--i;
			
		}
	}
}
