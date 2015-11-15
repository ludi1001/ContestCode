import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class B3 {
	static class Reservation implements Comparable<Reservation> {
		long start;
		long end;
		String id;
		Reservation(String s, long a, long b) {
			id = s;
			start = a;
			end = b;
		}
		
		public int compareTo(Reservation other) {
			if(this.end < other.end)
				return -1;
			if(this.end > other.end)
				return 1;
			if(this.start < other.start)
				return -1;
			if(this.start > other.start)
				return 1;
			return id.compareTo(other.id);
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
				in.nextLine();
				try {
					list.add(new Reservation(id, format.parse(enter).getTime(), format.parse(leave).getTime() + C * 60 * 1000));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Collections.sort(list);
			ArrayList<Long> room = new ArrayList<Long>();
			for(Reservation r : list) {
				boolean fit = false;
				System.out.println(format.format(new Date(r.start)) + " " + format.format(new Date(r.end)));
				for(int i = 0; i < room.size(); ++i) {
					if(room.get(i) <= r.start) {
						fit = true;
						room.set(i, r.end);
						break;
					}
				}
				if(!fit)
					room.add(r.end);
				//System.out.println(room);
			}
			System.out.println(room.size());
		}
	}
}
