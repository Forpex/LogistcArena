/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

/**
 * @author Andreas Stock
 *
 */
public interface Location {

	public int distance(Location p, Boolean beyondNextNodes);
	public Location next();
	public Location next(Location towardsDestination);
	public Location turn();
	int distanceMessureRekursion(Location p, ArrayList<Location> alreadyvisited);
}
