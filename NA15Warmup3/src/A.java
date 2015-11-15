import java.util.Scanner;
public class A {
	public static int numDigits(int num) {
		int d = 1;
		num /= 10;
		while(num > 0) {
			num /= 10;
			++d;
		}
		return d;
	}
	public static int[] getDigits(int num) {
		int[]digits = new int[numDigits(num)];
		for (int i = digits.length - 1; i >= 0; --i) {
			digits[i] = num % 10;
			num /= 10;
		}
		return digits;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int A = in.nextInt();
			int B = in.nextInt();
			if(A == 0 && B == 0) break;
			
			String[]box = {
					"+---+",
					"|  /|",
					"| / |",
					"|/  |",
					"+---+"
			};
			int nA = numDigits(A);
			int nB = numDigits(B);
			int[]dA = getDigits(A);
			int[]dB = getDigits(B);
			int x = 0, y = 0;
			char[][]map = new char[4*nB + 5][4*nA + 5];
			for (int i = 0; i < map.length; ++i) {
				for (int j = 0; j < map[0].length; ++j)
					map[i][j] = ' ';
			}
			for(int i = 0; i < map[0].length; ++i) {
				map[0][i] = '-';
				map[map.length - 1][i] = '-';
			}
			for(int i = 0; i < map.length; ++i) {
				map[i][0] = '|';
				map[i][map[0].length - 1] = '|';
			}
			map[0][0] = map[0][map[0].length - 1] = map[map.length - 1][0] = map[map.length-1][map[0].length-1] = '+';
			for (int r = 0; r < nB; ++r) {
				for (int c = 0; c < nA; ++c) {
					x = 2 + c * 4;
					y = 2 + r * 4;
					for (int i = 0; i < box.length; ++i) {
						for (int j = 0; j < box[0].length(); ++j) {
							map[y+i][x+j] = box[i].charAt(j);
						}
					}
					
					int prod = dA[c] * dB[r];
					map[y+1][x+1] = (char)((prod/10) + (int)'0');
					map[y+3][x+3] = (char)((prod % 10) + (int)'0');
				}
			}
			
			for (int i = 0; i < nA; ++i) {
				map[1][4 + 4*i] = (char)(dA[i] + (int)'0');
			}
			for (int i = 0; i < nB; ++i) {
				map[4 + 4*i][map[0].length - 2] = (char)(dB[i] + (int)'0');
			}
			
			int bigprod = A * B;
			int[]dP = getDigits(bigprod);
			x = 3;
			for (int i = nA; i > 0; --i) {
				y = map.length - 2;
				map[y][x] = (char)(dP[dP.length - i] + (int)'0');
				map[y][x+2] = '/';
				x += 4;
			}
			y = map.length - 2;
			for (int i = dP.length - nA - 1; i >= 0; --i) {
				x = 1;
				map[y][x] = '/';
				map[y-2][x] = (char)(dP[i] + (int)'0');
				y -= 4;
			}
			map[map.length-2][map[0].length-4] = ' ';
			
			for (int i = 0; i < map.length; ++i) {
				for (int j = 0; j < map[0].length; ++j) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

}
