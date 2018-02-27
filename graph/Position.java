/**
 * 
 */
package graph;

/**
 * @author Andreas Stock
 *
 */
public interface Position {

	public int distance(Position p, Boolean justIntel);
	public Position next();
	public Position next(Edge n);
	public Position moveback();
	
}
