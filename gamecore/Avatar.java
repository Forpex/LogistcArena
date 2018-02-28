/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import controls.Client;
import gamecore.graph.Position;
import gfx.Displayable;

/**
 * @author Andreas Stock
 *
 */
public class Avatar implements Displayable{
	private static final int STAYING = 0;
	
	Position position;

	public Boolean isStaying() {
		return chosenPath<=0;
	}
	
	/**
	 * only interesting if fighter is on node?
	 *	 is the choice of edge to step on on next iteration. 
	 *	 if no path is chosen, just stay at node until change.
	 * its an index for the list of edges on the node. can it be an edge directly?
	 * if its<0, stay!
	 */
	int chosenPath = STAYING;
	
	
	//properties
	int health = 125;
	int armor = 0;
	ArrayList<Weapon> weapons = giveAllWeaponsWithoutAmmo();
	
	Client client;
	
	public Avatar(Client client, Position startingPointOnGraph) {
		this.client = client;
		this.position = startingPointOnGraph;
		
	}
	
	public static ArrayList<Weapon> giveAllWeaponsWithoutAmmo() {
		// TODO Auto-generated method stub
		//call constructors of all weapons and then add them. 
		
		return null;
	}
	
}
