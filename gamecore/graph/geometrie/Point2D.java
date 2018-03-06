/**
 * 
 */
package gamecore.graph.geometrie;

/**
 * 
 * represents a point in 2D space.
 * @author Andreas Stock
 *
 */
public class Point2D {

	public double x;
	public double y;
	
	/**
	 * 
	 */
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point2D(Point2D relativePosition1, double d1, Point2D relativePosition2, double d2) {
		if (d1 == 0) {
			this.x = relativePosition1.x;
			this.y = relativePosition1.y;
		} else {
			if (d2 == 0) {
				this.x = relativePosition2.x;
				this.y = relativePosition2.y;
			} else {
				double skalar = 1/(d2+d1);
				this.x = (relativePosition2.x*d1 + relativePosition1.x*d2) * skalar;
				this.y = (relativePosition2.y*d1 + relativePosition1.y*d2) * skalar;
			}
		}
	}

}
