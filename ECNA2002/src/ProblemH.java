import java.util.*;
public class ProblemH {
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		int n = 0;
		while((n = scanner.nextInt()) != 0) {
			int num = n*(n + 1)/2;
			char[]c = scanner.next().toCharArray();
		
			boolean won;
			int x = 0;
			boolean[]yes = new boolean[26];
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < i; ++j) {
					won = false;
					int y = i*(i+1)/2;
					int z = y;
					for(int k = i + 1; k < n; ++k) {
						y += k;
						z += k + 1;

						int a = y;
						int b = z;
						int max = b - 1;
						while(b <= max) {
							System.out.println(x + " " + a + " " + b);
							if(c[x] == c[a] && c[a] == c[b]) {
								won = true;
								break;
							}
							a++;
							b = b + 1 - k;
						}
						if(won)
							break;
					}
					if(won) {
						yes[c[x] - 'a'] = true;
					}
					x++;
				}
			}
			won = false;
			for(int i = 0; i < 26; ++i) {
				if(yes[i]) {
					System.out.print((char)(i + 'a'));
					won = true;
				}
			}
			if(!won)
				System.out.print("LOOOOOOOOSER!");
			System.out.println();
		}
	}
}
