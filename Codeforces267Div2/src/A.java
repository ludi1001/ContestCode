import java.util.Scanner;
public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int count = 0;
		for(int i = 0; i < n; ++i)
			if(in.nextInt()+2 <= in.nextInt()) ++count;
		System.out.println(count);
	}

}
