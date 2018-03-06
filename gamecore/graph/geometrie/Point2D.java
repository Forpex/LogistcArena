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

	public Point2D(Point2D relativePosition1, Integer distance1, Point2D relativePosition2, Integer distance2) {
		if (distance1 == 0) {
			this.x = relativePosition1.x;
			this.y = relativePosition1.y;
		} else {
			if (distance2 == 0) {
				this.x = relativePosition2.x;
				this.y = relativePosition2.y;
			} else {
				double skalar = distance1/(distance2+distance1);
				this.x = relativePosition1.x + (-relativePosition2.x + relativePosition1.x) * skalar;
				this.y = relativePosition1.y + (-relativePosition2.y + relativePosition1.y) * skalar;
			}
		}
	}

}
