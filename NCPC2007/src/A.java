import java.util.*;
public class A {
	public static void main(String[]args) throws Exception {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t-- > 0) {
			int n = in.nextInt(); in.nextLine();
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 0; i < n; ++i)
				list.add(in.nextLine());
			boolean works = true;
			for(int i = 0; i < n; ++i) {
				for(int j = 0; j < n; ++j)
					if(i != j && list.get(i).startsWith(list.get(j))) {
						works = false;
						break;
					}
				if(!works)
					break;
			}
			System.out.println(works ? "YES" : "NO");
		}
	}
}
