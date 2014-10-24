import java.util.*;
public class E {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		double v = in.nextDouble();
		int n = in.nextInt();
		int best = 0;
		double closest = Double.MAX_VALUE;
		int k = 0;
		while(n-- > 0) {
			double a = in.nextDouble();
			double b = in.nextDouble();
			double h = in.nextDouble();
			double step = h/1e6;
			double integral = 0.0;
			double x = 0.0;
			double temp = 0.0;
			while(x < h + step/2) {
				temp = (a * Math.exp(-x*x) + b * Math.sqrt(x));
				integral += Math.PI * temp * temp * step;
				x += step;
			}
			integral = integral - Math.PI * a * a * step/2;
			integral = integral - Math.PI * temp * temp * step/2;
			//System.out.println(integral);
			double diff = Math.abs(integral - v);
			if(diff < closest) {
				closest = diff;
				best = k;
			}
			++k;
			//System.out.println(closest);
			//System.out.println(best);
		}
		System.out.println(best);
	}
}
