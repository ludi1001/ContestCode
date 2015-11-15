import java.util.*;
public class D2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
		int m = in.nextInt();
		int n = in.nextInt();
		ArrayList<Integer> marks = new ArrayList<Integer>();
		for(int i = 0; i < n; ++i)
			marks.add(in.nextInt());
		Collections.sort(marks);
		Set<Integer> cover = new HashSet<Integer>(marks);
		System.out.println(go(marks, 0, cover, cover, m));
		}
	}
	public static int go(ArrayList<Integer> marks, int index, Set<Integer> markSet, Set<Integer> cover, int m) {
		//System.out.println("@" + marks.get(index));
		if(cover.size() == 1 && cover.contains(marks.get(index))) {
			//System.out.println("auto full cover");
			return 1;
		}
		if(index == marks.size() - 1) {
			//System.out.println("impossible");
			return Integer.MAX_VALUE;
		}
		int minCover = Integer.MAX_VALUE;
		for(int i = index + 1; i < marks.size(); ++i) {
			int diff = marks.get(i) - marks.get(index);
			//System.out.println("@" + marks.get(index) + " trying with diff " + diff);
			boolean works = true;
			boolean removes = false;
			for(int j = marks.get(index)%diff; j < m; j+=diff) {
				if(!markSet.contains(j)) {
					works = false;
					//System.out.println("failed b/c " + j);
					break;
				}
				if(!removes && cover.contains(j) && j != marks.get(index))
					removes = true;
			}
			if(!removes);
				//System.out.println("no removal");
			if(works && removes) {
				Set<Integer> newCover = new HashSet<Integer>(cover);
				for(int j = marks.get(index)%diff; j < m; j += diff) {
					newCover.remove(j);
				}
				if(newCover.size() == 0) {
					//System.out.println("full cover");
					return 1; //covered everything
				}
				int s = 0;
				//System.out.println("new cover" + newCover);
				while(!newCover.contains(marks.get(s)))
					++s;
				int c = 1 + go(marks, s, markSet, newCover, m);
				if(c < minCover)
					minCover = c;
				//System.out.println("@" + marks.get(index) + " mc" + minCover);
			}
		}
		//System.out.println("best min cover " + minCover);
		HashSet<Integer> newCover = new HashSet<Integer>(cover);
		newCover.remove(marks.get(index));
		int s = 0;
		while(!newCover.contains(marks.get(s)))
			++s;
		int c = 1 + go(marks, s, markSet, newCover, m);
		if(c < minCover)
			minCover = c;
		//System.out.println("@" + marks.get(index) + " best " + c);
		return minCover;
	}
}
