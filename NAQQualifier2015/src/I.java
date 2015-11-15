import java.util.Scanner;
public class I {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); in.nextLine();
		while(N-->0) {
			String s = in.nextLine();
			if(s.startsWith("Simon says ")) {
				System.out.println(s.substring("Simon says ".length()));
			}
		}
	}

}
