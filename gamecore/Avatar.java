/**
 * 
 */
package gamecore;

import graph.Position;

import controls.Client;

/**
 * @author Andreas Stock
 *
 */
public class Avatar {
	private static final int STAYING = -1;
	
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
	int[] ammo = new int[Weapons.size];
	
	Client client;
	
	
}
