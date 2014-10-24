import java.util.ArrayList;
import java.util.Scanner;


public class C {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final double eps = 0.000000001;
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			double[]b = new double[4];
			double[]t = new double[4];
			for(int i = 0; i < 4; ++i)
				b[i] = in.nextDouble();
			for(int i = 0; i < 4; ++i)
				t[i] = in.nextDouble();
			double[]d = new double[4];
			for(int i = 0; i < 4; ++i)
				d[i] = b[i] - t[i];
			double discr = 4*d[2]*d[2] - 12 *d[3]*d[1];
			ArrayList<Double> roots = new ArrayList<Double>();
			roots.add(1.0);
			roots.add(0.0);
			if(Math.abs(discr) < eps || discr > 0) {
				if(Math.abs(d[3]) < eps) {
					if(Math.abs(d[2]) < eps) {
					}
					else {
						roots.add(-d[1]/2/d[2]);
					}
				}
				else {
					double r1 = (-2*d[2]+Math.sqrt(discr))/(6*d[3]);
					double r2 = (-2*d[2]-Math.sqrt(discr))/(6*d[3]);
					if (-eps < r1 && r1 < 1 + eps)
						roots.add(r1);
					if(-eps < r2 && r2 < 1 + eps)
						roots.add(r2);
				}
			}
			double max = eval(d,roots.get(0));
			double min = eval(d, roots.get(0));
			for(int i = 1; i < roots.size(); ++i) {
				if(eval(d,roots.get(i)) > max)
					max = eval(d,roots.get(i));
				if(eval(d,roots.get(i)) < min)
					min = eval(d,roots.get(i));
			}
			System.out.printf("%.6f\n", max - min);
		}
	}
	public static double eval(double[]b,double x) {
		return b[0] + b[1]*x + b[2]*x*x + b[3]*x*x*x;
	}

}
