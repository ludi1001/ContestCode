import java.util.Scanner;


public class K {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int m = 0;
		int[]lens = new int[100];
		int i = 0;
		while(in.hasNextLine()) {
			String line = in.nextLine();
			lens[i] = line.length();
			if(lens[i] > m)
				m = lens[i];
			i++;
		}
		int rval = 0;
		for(int j = 0; j < i - 1; ++j) {
			rval += (m - lens[j])*(m - lens[j]);
		}
		System.out.println(rval);
	}

}
