import java.util.Scanner;
public class E {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cloud = 1;
		while(true) {
			int W = in.nextInt();
			int N = in.nextInt();
			if(W == 0 && N == 0) break;
			int[]counts = new int[N];
			int[]letters = new int[N];
			int cmax = 0;
			for(int i = 0; i < N; ++i) {
				in.nextLine();
				String word = in.next();
				int count = in.nextInt();
				letters[i] = word.length();
				counts[i] = count;
				if (counts[i] > cmax) {
					cmax = counts[i];
				}
			}
			
			int height = 0;
			int rw = W;
			int hmax = 0;
			for(int i = 0; i < N; ++i) {
				int temp = 40*(counts[i]-4);
				int P = 8 + temp / (cmax - 4);
				if (temp % (cmax - 4) != 0) {
					P += 1;
				}
				temp = 9 * letters[i] * P;
				int w = temp / 16;
				if (temp % 16 != 0) w += 1;
				
				if (rw < w) {
					rw = W;
					height += hmax;
					hmax = 0;
				}
				rw -= w + 10;
				hmax = Math.max(hmax, P);
			}
			height += hmax;
			System.out.println("CLOUD " + cloud + ": " + height);
			++cloud;
		}
	}

}
