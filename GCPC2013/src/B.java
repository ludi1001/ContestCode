import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class B {
	static class Reservation implements Comparable<Reservation>{
		long time;
		int value;
		Reservation(long t, int v) {
			time = t;
			value = v;
		}
		public int compareTo(Reservation other) {
			if(this.time < other.time)
				return -1;
			if(this.time > other.time)
				return 1;
			return this.value - other.value;
		}
	}
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		while(T-- > 0) {
			int B = in.nextInt();
			int C = in.nextInt();
			ArrayList<Reservation> list = new ArrayList<Reservation>();
			for(int i = 0; i < B; ++i) {
				String id = in.next();
				String enter = in.next() + " " + in.next();
				String leave = in.next() + " " + in.next();
				if(i != B - 1)
					in.nextLine();
				try {
					list.add(new Reservation(format.parse(enter).getTime(), 1));
					list.add(new Reservation(format.parse(leave).getTime() + C * 60 * 1000, -1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Collections.sort(list);
			int max = 0;
			int sum = 0;
			for(Reservation r : list) {
				sum += r.value;
				if(sum > max)
					max = sum;
			}
			System.out.println(max);
		}
	}
}
