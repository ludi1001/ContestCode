import java.util.*;
public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		in.nextLine();
		for(int t = 0; t < testCases; ++t) {
			String line = in.nextLine();
			int dist = 0;
			int szeros = 0;
			for(int i = 1; i < line.length(); ++i)
				if(line.charAt(i) != 'A') {
					szeros = i - 1;
					break;
				}
			int ezeros = 0;
			for(int i = line.length() - 1; i >= 1; --i) {
				if(line.charAt(i) == 'A')
					++ezeros;
				else
					break;
			}
			dist = Math.min(line.length() - ezeros - 1, line.length() - szeros - 1);
			//going forward, turning back around
			for(int i = szeros + 1; i < line.length() - ezeros; ++i) {
				if(line.charAt(i) == 'A') {
					//num zeros
					int temp = i + 1;
					while(i < line.length() && line.charAt(i) == 'A') ++i;
					dist = Math.min(dist, 2*(temp - 2) + line.length() - i);
				}
			}
			//going backward, turning back around
			for(int i = line.length() - ezeros - 1; i > szeros; --i) {
				if(line.charAt(i) == 'A') {
					int temp = i - 1;
					while(i >= 0 && line.charAt(i) == 'A') --i;
					dist = Math.min(dist, 2*(line.length() - temp - 2) + i);
				}
			}
			for(int i = 0; i < line.length(); ++i) {
				int d = line.charAt(i) - 'A';
				if(d > 13)
					d = 26 - d;
				dist += d;
			}
			System.out.println(dist);
		}
	}

}
