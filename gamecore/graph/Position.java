/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

/**
 * @author Andreas Stock
 *
 */
public interface Position {

	public static final int ALREADY_VISITED = 1000;
	public int distance(Position p);
	public Position next();
	public Position next(Position towardsDestination);
	int distanceMessureRekursion(Position p, ArrayList<Position> alreadyvisited);
}
