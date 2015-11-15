import java.util.Scanner;


public class quiteaproblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String line = in.nextLine().toLowerCase();
			if(line.indexOf("problem") != -1)
				System.out.println("yes");
			else
				System.out.println("no");
		}
		in.close();
	
	}

}
