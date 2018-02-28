/**
 * 
 */
package controls;

import gamecore.Intel;

/**
 * @author Andreas Stock
 *
 * Controls: Keyboard, RandomBot, etc.
 */
public abstract class Client {

	int id;
	String name;
	Intel lastIntelGotten;
	
	public Client(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
}
