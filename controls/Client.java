/**
 * 
 */
package controls;

import gamecore.intel.Intel;

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
	private static final int STAYING = -1;
	public Boolean isStaying() {
		return chosenPath<=STAYING;
	}
	
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

	public int getID() {
		return id;
	}
}
