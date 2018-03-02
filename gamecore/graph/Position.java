/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

import gamecore.Avatar;

/**
 * @author Andreas Stock
 *
 */
public interface Position {

	ArrayList<Avatar> avatars = null;
	public int distance(Position p, Boolean justIntel);
	public Position next();
	public Position next(int chosenEdgeID);
	//public Position next(Position towardsDestination);
	public int getNumPathChoices();
	public Position turn();
	
	
	
}
