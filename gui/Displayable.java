/**
 * 
 */
package gui;

import java.awt.geom.Point2D;

/**
 * This interface can be used everywhere to display stuff in the Gui. please use!
 * @author Andreas Stock
 *
 */
public interface Displayable {

	void draw(Point2D center, double scale);
	//more?
}
