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
	/**
	 * If Path==0, stay.
	 * On Edge, if Path==number, go the way of that index.
	 */
	int chosenPath;
	
	/**
	 * @return the chosenPath
	 */
	public int getChosenPath() {
		return chosenPath;
	}

	public Client(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public abstract void post(Intel intel);
}
