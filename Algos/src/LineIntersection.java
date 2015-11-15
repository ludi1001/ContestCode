import java.awt.geom.Point2D;


public class LineIntersection {
	/**
	 * Finds intersection of lines a1 x + b1 y = c1 and a2 x + b2 y + c2
	 * @param a1
	 * @param b1
	 * @param a2
	 * @param b2
	 * @return
	 */
	public Point2D.Double intersectionPt(double a1, double b1, double c1, double a2, double b2, double c2) {
		double det = a1 * b2 - b1 * a2;
		if(det == 0)
			; //complain, lines are parallel or coincide
		double x = (c1 * b2 - c2 * b1)/det;
		double y = (a1 * c2 - c1 * a2)/det;
		return new Point2D.Double(x,y);
	}
	public static void main(String[]args) {
		LineIntersection lt = new LineIntersection();
		System.out.println(lt.intersectionPt(0, 1, 2, 1, 0, 4)); //(4,2)
		System.out.println(lt.intersectionPt(1, 1, 1, -1, 1, 0)); //(1/2, 1/2)
		System.out.println(lt.intersectionPt(2, 3, 5, -2, 4, 2)); //(1,1)
		System.out.println(lt.intersectionPt(1,2,3,4,5,6)); //(-1,2)
	}
}
