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

	public int distance(Position p, Boolean beyondNextNodes);
	public Position next();
	public Position next(Position towardsDestination);
	public Position turn();
	int distanceMessureRekursion(Position p, ArrayList<Position> alreadyvisited);
}
