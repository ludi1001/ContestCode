import java.util.*;
public class G {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while(t > 0) {
			int j = in.nextInt();
			int p = in.nextInt();
			int H = in.nextInt();
			int L = in.nextInt();
			double[] res = skijump(j, p, H, L);
			//System.out.printf("%.10g %.10g %.10g\n", res[0], res[1], res[2]);
			print(res[0]);
			System.out.print(" ");
			print(res[1]);
			System.out.print(" ");
			print(res[2]);
			System.out.println();
			--t;
		}
	}
	public static void print(double d) {
		int i = (int)d;
		int digits = 0;
		int temp = i;
		while(temp > 0) {
			temp /= 10;
			++digits;
		}
		System.out.printf("%." + (10-digits) + "f", d);
	}
	public static double[] skijump(double j, double p, double H, double L) {
		double eps = 0.00001;
		double g = 9.81;
		double l = 0;
		double v = 0;
		double th = 0;
		if (H+p-(L*L)/(16*j) - H/2 > eps) {  //above jump at midpoint
			
			if (H+p-(L*L)/(4*j) > -eps) {						// above jump at end
				l = Math.sqrt(4*j*(H+p));
				v = Math.sqrt(2*g*(H+p+j));
				th = Math.atan(l/(2*j));
				th = th/Math.PI*180;
			}
			else {
			
			//hit in second half of course
			
			double a = 2*H/(L*L) + 1/(4*j);
			double b = -4*H/(L);
			double c = H-p;
			
			l = (-b+Math.sqrt(b*b-4*a*c))/(2*a);
			v = Math.sqrt(2*g*(j+l*l/(4*j)));
			//th = Math.atan2(1, -l/2/j) - Math.atan2(1, 4*H/L*(l/L -1));//
			th = dotangle(4*H*(L-l)/(L*L), l/(2*j));
					th = th/Math.PI *180;
			}
		}
		else { 
			// hit in first half of course
			
			double a = 1/(4*j) - 2*H/(L*L);
			l = Math.sqrt(p/a);
			v = Math.sqrt(2*g*(j+l*l/(4*j)));
			//th = Math.atan2(1, -l/2/j)-Math.atan2(1, -4*H*l/L/L);//
			th = dotangle(4*H*l/(L*L), l/(2*j));
			th = th/Math.PI*180;
		}
		return new double[] { l, v, th};
	}
	
	public static double dotangle(double m1, double m2) {
		double num = 1+m1*m2;
		double den = Math.sqrt((1+m1*m1)*(1+m2*m2));
		return Math.acos(num/den);
	}

}
