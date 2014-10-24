import java.util.*;
import java.awt.Point;
public class F {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final double EPS = 1e-8;
		while(in.hasNextLine()) {
			String[]t = in.nextLine().split(" ");
			ArrayList<Point> comp = new ArrayList<Point>();
			if(t[1].charAt(0) != 'n') {
				for(int i = 1; i < t.length; ++i) {
					String[]parts = t[i].split(",");
					comp.add(new Point(Integer.parseInt(parts[0].substring(1)),
									Integer.parseInt(parts[1].substring(0, parts[1].length()-1))));	
				}
			}

     		ArrayList<Point> orcs = new ArrayList<Point>();
			t = in.nextLine().split(" ");
			if(t[1].charAt(0) != 'n') {
				for(int i = 1; i < t.length; ++i) {
					String[]parts = t[i].split(",");
					orcs.add(new Point(Integer.parseInt(parts[0].substring(1)),
									Integer.parseInt(parts[1].substring(0, parts[1].length()-1))));	
				}
			}
			if(comp.size() <= 1) {
				System.out.println(0); continue;
			}
			double min_radius = 1e30;
			for(int i = 0; i < comp.size(); ++i) {
				for(int j = i + 1; j < comp.size(); ++j) {
					Point p1 = comp.get(i);
					Point p2 = comp.get(j);
					
					double vy = p2.x - p1.x;
					double vx = -(p2.y - p1.y);
					
					double midx = (p1.x + p2.x)/2.0;
					double midy = (p1.y + p2.y)/2.0;
					
					double lo = -1e30;
					double hi = 1e30;
					double vlo = -1e30;
					double vhi = 1e30;
					boolean fail = false;
					for(int k = 0; k < comp.size(); ++k) {
						if(k == i || k == j) continue;
						Point p = comp.get(k);
						double mid2x = (p1.x + p.x)/2.0;
						double mid2y = (p1.y + p.y)/2.0;
						double v2y = p.x - p1.x;
						double v2x = -(p.y - p1.y);
						double d = (v2x*mid2y-v2y*mid2x+v2y*midx-v2x*midy)/(v2x*vy-v2y*vx);
						if(Math.abs(v2x*vy-v2y*vx) < EPS) { //collinear
							if((midx-p1.x)*(midx-p1.x) + (midy-p1.y)*(midy-p1.y) < EPS + (midx-p.x)*(midx-p.x) + (midy-p.y)*(midy-p.y)) {
								fail = true;
								break; //guaranteed not to be in circle
							}
							else continue; //guaranteed to be in cirle
						}
						//System.out.println(p1 + ", " + p2 + ", " + p + "(" + midx + ", " + midy + "), (" + mid2x + "," + mid2y + "): " + d);
						double cross = (p2.x-p1.x)*(p.y-p1.y)-(p.x-p1.x)*(p2.y-p1.y);
						if(cross < 0) {
							if(d < hi) hi = d;
						}
						else {
							if(d > lo) lo = d;
						}
					}
					if(fail) continue;
					for(int k = 0; k < orcs.size(); ++k) {
						Point p = orcs.get(k);
						double mid2x = (p1.x + p.x)/2.0;
						double mid2y = (p1.y + p.y)/2.0;
						double v2y = p.x - p1.x;
						double v2x = -(p.y - p1.y);
						double d = (v2x*mid2y-v2y*mid2x+v2y*midx-v2x*midy)/(v2x*vy-v2y*vx);
						if(Math.abs(v2x*vy-v2y*vx) < EPS) {
							if((midx-p1.x)*(midx-p1.x) + (midy-p1.y)*(midy-p1.y) < EPS + (midx-p.x)*(midx-p.x) + (midy-p.y)*(midy-p.y)) continue;
							else {
								fail = true;
								break;
							}
						}
						//System.out.println(p1 + ", " + p2 + ", " + p + "(" + midx + ", " + midy + "), (" + mid2x + "," + mid2y + "): " + d);
						double cross = (p2.x-p1.x)*(p.y-p1.y)-(p.x-p1.x)*(p2.y-p1.y);
						if(cross > 0) {
							if(d < vhi) vhi = d;
						}
						else {
							if(d > vlo) vlo = d;
						}
					}
					//System.out.print(p1 + " " + p2 + " :: ");
					//System.out.printf("%f %f %f %f %d\n", lo, hi, vlo, vhi, fail ? 1 : 0);
					if(fail) continue;
					if(Math.abs(vhi-vlo) < EPS) continue;
					if(hi + EPS > vhi) hi = vhi;
					if(lo < vlo + EPS) lo = vlo;
					if(lo + EPS > hi) continue;
					double d = Math.min(Math.abs(lo), Math.abs(hi));
					if(lo < -EPS && hi > EPS) d = 0;
					double rad = Math.sqrt((midx + d*vx - p1.x)*(midx + d*vx - p1.x)+(midy + d*vy - p1.y)*(midy + d*vy - p1.y));
					//System.out.println(rad);
					min_radius = Math.min(min_radius, rad);
				}
			}
			if(min_radius > 1e29)
				System.out.println("The Orcs are close");
			else
				System.out.println(min_radius);
		}
	}

}
