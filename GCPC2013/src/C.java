import java.util.*;
public class C {
	static int solx, soly;
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0) {
			int x1 = in.next().charAt(0) - 'A';
			int y1 = in.nextInt() - 1;
			int x2 = in.next().charAt(0) - 'A';
			int y2 = in.nextInt() - 1;
			in.nextLine();
			
			if((x1 + y1) % 2 != (x2 + y2) % 2) {
				System.out.println("Impossible");
				continue;
			}
			
			int[][]board = new int[8][8];
			mark(board, x1, y1);
			mark(board, x2, y2);
			
			soly += 1; y1 += 1; y2 += 1;
			if(x1 == solx && y1 == soly) {
				if(x2 == solx && y2 == soly)
					System.out.println("0 " + (char)(solx + 'A') + " " + soly);
				else {
					System.out.println("1 " + (char)(x1 + 'A') + " " + y1 + " " + (char)(x2 + 'A') + " " + y2);
				}
			}
			else if(x2 == solx && y2 == soly) {
				System.out.println("1 " +  (char)(x1 + 'A') + " " + y1 + " " +  (char)(x2 + 'A') + " " + y2);
			}
			else {
				System.out.println("2 " + (char) (x1 + 'A') + " " + y1 + " " +  (char)(solx + 'A') + " " + soly + " " +  (char)(x2 + 'A') + " " + y2); 
			}
		}
	}
	public static void mark(int[][]board, int x, int y) {
		board[x][y] += 1;
		for(int i = x + 1, j = y + 1; i < 8 && j < 8; i += 1, j+= 1) {
			board[i][j] += 1;
			if(board[i][j] == 2) {
				solx = i;
				soly = j;
			}
		}
		for(int i = x + 1, j = y - 1; i < 8 && j >= 0; i += 1, j -= 1) {
			board[i][j] += 1;
			if(board[i][j] == 2) {
				solx = i;
				soly = j;
			}
		}
		for(int i = x - 1, j = y + 1; i >= 0 && j < 8; i -= 1, j += 1) {
			board[i][j] += 1;
			if(board[i][j] == 2) {
				solx = i;
				soly = j;
			}
		}
		for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i -= 1, j -=1) {

			board[i][j] += 1;
			if(board[i][j] == 2) {
				solx = i;
				soly = j;
			}
		}
	}
}
