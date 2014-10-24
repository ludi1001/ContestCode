import java.util.*;
public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> arr = new ArrayList<String>();
		while(true) {
			double A = in.nextDouble();
			String U = in.next();
			double R = in.nextDouble();
			String V = in.nextLine().substring(1);
			if(A < 0) break;
			double P = A/R * 100;
			if(P+.0000000001 < 1.0) {
				arr.add(V);
			}
			else
				System.out.printf("%s %.1f %s %.0f%%\n", V, A, U, P);
		}
		System.out.println("Provides no significant amount of:");
		for(String s : arr)
			System.out.println(s);
	}

}
