import java.util.*;
public class ProblemB {
	final static double eps = 0.00001;
	static class Interval implements Comparable<Interval> {
		double first;
		double second;
		@Override
		public int compareTo(Interval arg0) {
			if(Math.abs(first - arg0.first) < eps) {
				if(Math.abs(second - arg0.second) < eps) {
					return 0;
				}
				else {
					if(second < arg0.second)
						return -1;
					else
						return 1;
				}
			}
			else {
				if(first < arg0.first)
					return -1;
				else
					return 1;
			}
		}
		@Override
		public String toString() {
			return "(" + first + ", " + second + ")";
		}
	}
	public static void main(String[]args) {
		Scanner scanner = new Scanner(System.in);
		double L = 0.0;
		ArrayList<Interval> list = new ArrayList<Interval>();
		while((L = scanner.nextDouble()) > 0.0) {
			list.clear();
			//System.out.println(L);
			while(true) {
				Interval v = new Interval();
				v.first = scanner.nextDouble();
				v.second = scanner.nextDouble();
				if(v.first > v.second) {
					break;
				}
				list.add(v);
			}
			Collections.sort(list);
			//System.out.println(list);
			double length = 0.0;
			double last = 0.0;
			for(Interval v : list) {
				if(v.first > last)
					length += v.first - last;
				if(v.second > last)
					last = v.second;
			}
			if(last < L)
				length += L - last;
			System.out.printf("The total planting length is %.1f\n", length);
		}
		scanner.close();
	}
}
