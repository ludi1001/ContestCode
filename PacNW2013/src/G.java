import java.util.Scanner;
public class G {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		long[]tribble = new long[68];
		tribble[0] = tribble[1] = 1;
		tribble[2] = 2;
		tribble[3] = 4;
		for(int i = 4; i < tribble.length; ++i)
			for(int j = 1; j <= 4; ++j)
				tribble[i] += tribble[i-j];
		int t = in.nextInt();
		while(t-- > 0) 
			System.out.println(tribble[in.nextInt()]);
	}
}
