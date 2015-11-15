import java.util.*;
import java.awt.Point;
public class C {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		boolean live = true;
		while(live) {
			ArrayList<String> map_temp = new ArrayList<String>();
			String s = "";
			while((live = in.hasNextLine()) && !(s = in.nextLine()).equals(""))
				map_temp.add(s);
			int index = 0;
			int[][]map = new int[map_temp.get(0).length()][map_temp.size()];
			ArrayList<Point> lookup = new ArrayList<Point>();
			int start = 0;
			int start_square = 0;
			int goal = 0;
			for(int j = 0; j < map[0].length; ++j) {
				for(int i = 0; i < map.length; ++i) {
					char c = map_temp.get(j).charAt(i);
					if(c == '.') {
						lookup.add(new Point(i, j));
						map[i][j] = index++;
					}
					else if(c == '#') {
						map[i][j] = -1;
					}
					else if(c == 'P') {
						lookup.add(new Point(i, j));
						start |= 1 << index;
						map[i][j] = index++;
					}
					else if(c == 'C') {
						lookup.add(new Point(i, j));
						start |= index << 26;
						start_square = index;
						map[i][j] = index++;
					}
					else if(c == 'G'){
						lookup.add(new Point(i, j));
						goal = index;
						map[i][j] = index++;
					}
					else System.out.println("ERROR INPUT");
				}
			}
			
			HashSet<Integer> visited = new HashSet<Integer>();
			Queue<Integer> qu = new LinkedList<Integer>();
			visited.add(start);
			qu.add(start);
			qu.add(-1);
			
			int[]dir_x = {1, 0, -1, 0};
			int[]dir_y = {0, 1, 0, -1};
			int[][] shifts = { {6, 4, 3, 1, 5, 2}, {3, 5, 2, 4, 1, 6}, {4, 6, 3, 2, 5, 1}, {5, 3, 1, 4, 2, 6} };
			int res = 0;
			boolean success = false;
			while(!qu.isEmpty()) {
				int state = qu.poll();
				if(state == -1) {
					if(qu.isEmpty())
						break;
					qu.add(-1);
					++res;
					continue;
				}
				
				int cur_loc = state >> 26;
				int box_state = (state >> 20) & (0x3F);
				
				if(cur_loc == goal && box_state == 0x3F) {
					success = true;
					break;
				}
				
				int x = lookup.get(cur_loc).x;
				int y = lookup.get(cur_loc).y;
				for(int d = 0; d < 4; ++d) {
					int new_x = x + dir_x[d];
					int new_y = y + dir_y[d];
					if(new_x < 0 || new_x >= map.length || new_y < 0 || new_y >= map[0].length) continue;
					if(map[new_x][new_y] == -1) continue;
					
					int new_loc = map[new_x][new_y];
					int new_state = state & 0xFFFFF;
					
					//rotate box
					int new_box_state = 0;
					for(int k = 0; k < 6; ++k) {
						new_box_state |= ((box_state >> (shifts[d][k]-1)) & 1) << k;
					}
					//if(new_loc != goal && new_loc != start_square) {
						boolean paint_on_cube = (new_box_state & 2) > 0;
						//remove paint
						new_box_state &= ~2;
						//check for paint on map
						if((state & (1 << new_loc)) > 0) {
							new_box_state |= 2;
							new_state &= ~(1 << new_loc);
						}
						if(paint_on_cube) {
							new_state |= (1 << new_loc);
						}
					//}
					new_state |= new_loc << 26;
					new_state |= new_box_state << 20;
					if(!visited.contains(new_state)) {
						visited.add(new_state);
						//(new_state, lookup);
						//System.out.print("keep state? ");
						//int p = in.nextInt();
						//if(p == 1)
						qu.add(new_state);
					}
				}
			}
			if(!success) res = -1;
			System.out.println(res);
		}
	}
	static void printState(int state, ArrayList<Point> lookup) {
		int cur_loc = state >> 26;
		int box_state = (state >> 20) & (0x3F);
		int x = lookup.get(cur_loc).x;
		int y = lookup.get(cur_loc).y;
		System.out.printf("%x %d %d %x(%s) [%s]\n", state, x, y, box_state, Integer.toBinaryString(box_state), Integer.toBinaryString(state));
		
	}
}
