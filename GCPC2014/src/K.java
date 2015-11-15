import java.util.*;
public class K {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int c = in.nextInt();
		int div = n/3;
		int mod = n%3;
		boolean yup = false;
		if(mod == 0 || mod == 1) {
			if(c > div && c <= n-div) yup = true;
		}
		else if(mod == 2) {
			if(c > div && c < n-div) yup = true;
		}
		if(yup) System.out.println("YES");
		else System.out.println("NO");
	}
}
