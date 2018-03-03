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

	public int distance(Position p, Boolean justIntel);
	public Position next();
	public Position next(int chosenEdgeID);
	//public Position next(Position towardsDestination);
	public int getNumOutgoingEdges();
	public Position turn();
	
	int distanceMessureRekursion(Position p, ArrayList<Position> alreadyvisited);
}
