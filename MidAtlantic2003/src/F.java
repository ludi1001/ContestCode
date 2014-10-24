import java.util.*;
import java.awt.*;
import java.awt.geom.Point2D;
public class F {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final double eps = 0.01;
		Scanner in = new Scanner(System.in);
		Point2D[]points = new Point2D[5];
		double[]dist = new double[3];
		double[]angles = new double[3];
		for(int i = 0; i < 5; ++i)
			points[i] = new Point2D.Double();
		while(true) {
			double first = in.nextDouble();
			if(!in.hasNextDouble())
				break;
			double second = in.nextDouble();
			points[0].setLocation(first, second);
			for(int i = 1; i < 3; ++i)
				points[i].setLocation(in.nextDouble(), in.nextDouble());
			points[3].setLocation(points[0]);
			points[4].setLocation(points[1]);
			
			String lc = "";
			String ac = "";
			
			for(int i = 0; i < 3; ++i)
				dist[i] = points[i].distance(points[i + 1]);
			Arrays.sort(dist);
			
			//check if it's a triangle
			if(dist[2] - dist[0] - dist[1] >= -eps) {
				System.out.println("Not a Triangle");
				continue;
			}
			
			if(Math.abs(dist[1] - dist[0]) < eps) {
				if(Math.abs(dist[2] - dist[1]) < eps)
					lc = "Equilateral";
				else
					lc = "Isosceles";
			}
			else if(Math.abs(dist[2] - dist[1]) < eps) {
				lc = "Isosceles";
			}
			else {
				lc = "Scalene";
			}

			for(int i = 0; i < 3; ++i) {
				double x1 = points[i + 1].getX() - points[i].getX();
				double x2 = points[i + 2].getX() - points[i].getX();
				double y1 = points[i + 1].getY() - points[i].getY();
				double y2 = points[i + 2].getY() - points[i].getY();
				
				double dot = x1 * x2 + y1 * y2;
				dot /= Math.sqrt(x1*x1 + y1*y1) * Math.sqrt(x2*x2 + y2*y2);
				angles[i] = Math.acos(dot);
			}
			ac = "Acute";
			for(int i = 0; i < 3; ++i) {
				if(Math.abs(angles[i] - Math.PI/2) < eps) {
					ac = "Right";
					break;
				}
				else if(angles[i] > Math.PI/2) {
					ac = "Obtuse";
					break;
				}
			}
			
			System.out.println(lc + " " + ac);
		}
		System.out.println("End of Output");
	}
}
