import java.util.Scanner;
public class E {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int g = in.nextInt();
		while(g-- > 0) {
			int num_rings = in.nextInt();
			int num_runes = in.nextInt();
			in.nextLine();
			int[][]runes = new int[num_runes][num_rings+1];
			boolean valid = true;
			int[][]lines = new int[num_runes][];
			for(int i = 0; i < num_runes; ++i) {
				String[] tokens = in.nextLine().split(" ");
				lines[i] = new int[tokens.length-1];
				for(int j = 0; j < tokens.length-1; ++j) {
					lines[i][j] = Integer.parseInt(tokens[j]);
					if(lines[i][j] == 0) {
						valid = false;
						break;
					}
				}
			}
			if(!valid) {
				System.out.println("INVALID: NULL RING");
				continue;
			}
			outer_1:
			for(int i = 0; i < num_runes; ++i) {
				for(int j = 0; j < lines[i].length; ++j) {
					if(lines[i][j] < -num_rings || lines[i][j] > num_rings) {
						valid = false;
						break outer_1;
					}
				}
			}
			if(!valid) {
				System.out.println("INVALID: RING MISSING");
				continue;
			}
			outer_2:
			for(int i = 0; i < num_runes; ++i) {
				for(int j = 0; j < lines[i].length; ++j) {
					if(runes[i][Math.abs(lines[i][j])] != 0) {
						valid = false;
						break outer_2;
					}
					if(lines[i][j] > 0)
						runes[i][lines[i][j]] = 1;
					else
						runes[i][-lines[i][j]] = -1;
				}
			}
			if(!valid) {
				System.out.println("INVALID: RUNE CONTAINS A REPEATED RING");
				continue;
			}
			
			boolean soln_found = false;
			for(int sol = 0; sol < (1 << num_rings); ++sol) {
				valid = true;
				for(int i = 0; i < runes.length; ++i) {
					boolean works = false;
					for(int j = 1; j <= num_rings; ++j) {
						if(runes[i][j] == 1) {
							if((sol & (1 << (j-1))) > 0) works = true;
						}
						else if(runes[i][j] == -1) {
							if((sol & (1 << (j-1))) == 0) works = true;
						}
						if(works) break;
					}
					if(!works) {
						valid = false;
						break;
					}
				}
				if(valid) {
					soln_found = true; break;
				}
			}
			if(soln_found)
				System.out.println("RUNES SATISFIED!");
			else
				System.out.println("RUNES UNSATISFIABLE! TRY ANOTHER GATE!");
		}
	}

}
