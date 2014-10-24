import java.util.*;
public class K {
	static class Point3D {
		double x;
		double y;
		double z;
		Point3D() {}
		Point3D(double a, double b, double c) {
			x = a;
			y = b;
			z = c;
		}
		public Point3D add(Point3D p) {
			return new Point3D(x + p.x, y + p.y, z + p.z);
		}
		public Point3D sub(Point3D p) {
			return new Point3D(x - p.x, y - p.y, z - p.z);
		}
		public double dot(Point3D p) {
			return x * p.x + y * p.y + z * p.z;
		}
		public Point3D cross(Point3D p) {
			Point3D vec = new Point3D();
			vec.x = this.y*p.z - this.z*p.y;
			vec.y = this.z*p.x - this.x*p.z;
			vec.z = this.x*p.y - this.y*p.x;
			return vec;
		}
		public Point3D mult(double val) {
			return new Point3D(val * x, val * y, val * z);
		}
	}
	static final double EPS = .00001;
	static Point3D intersects(Point3D q1, Point3D q2, Point3D norm) {
		double d1 = q1.dot(norm);
		double d2 = q2.dot(norm);
		boolean zero1 = Math.abs(d1) < EPS;
		boolean zero2 = Math.abs(d2) < EPS;
		if(zero1 && zero2)
			return null;
		if((d1 > EPS && d2 >EPS) || (d1 < -EPS && d2 < -EPS))
			return null;
		d1 = Math.abs(d1);
		d2 = Math.abs(d2);
		return q1.mult(d2/(d1 + d2)).add(q2.mult(d1/(d1 + d2)));
	}
	static boolean works(Point3D[]p, Point3D[]q) {
		Point3D norm = p[1].cross(p[2]);
		List<Point3D> inter = new ArrayList<Point3D>();
		for(int i = 0; i < 3; ++i) {
			int j = i + 1;
			if(j == 3)
				j = 0;
			Point3D tmp = intersects(q[i], q[j], norm);
			if(tmp == null) continue;
			inter.add(tmp);
		}
		if(inter.isEmpty()) return false;
		for(Point3D ip : inter) {
			List<Double> values = new ArrayList<Double>();
			for(int i = 0; i < 3; ++i) {
				int j = i + 1;
				if(j == 3)
					j = 0;
				values.add(ip.sub(p[i]).cross(p[j].sub(p[i])).dot(norm));
			}
			if(values.get(0) > 0 && values.get(1) > 0 && values.get(2) > 0)
				return true;
			if(values.get(0) < 0 && values.get(1) < 0 && values.get(2) < 0)
				return true;
		}
		return false;
	}
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		while(T-- > 0) {
			Point3D[]p = new Point3D[3];
			Point3D[]q = new Point3D[3];
			for(int i = 0; i < 3; ++i)
				p[i] = new Point3D(in.nextInt(), in.nextInt(), in.nextInt());
			for(int i = 0; i < 3; ++i)
				q[i] = new Point3D(in.nextInt(), in.nextInt(), in.nextInt());
			
			for(int i = 2; i >= 0; --i) {
				q[i] = q[i].sub(p[0]);
				p[i] = p[i].sub(p[0]);
			}
			System.out.println(works(p, q) ? "YES" : "NO");
		}
	}
}
