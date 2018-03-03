/**
 * 
 */
package controls;

import gamecore.Intel;
import gamecore.Settings;

/**
 * @author Andreas Stock
 *
 * Controls: Keyboard, RandomBot, etc.
 */
public abstract class Client extends Thread{

	int id;
	String name;
	Intel lastIntelGotten;
	/**
	 * If Path==-1, stay.
	 * On Edge, if Path==number, go the way of that index.
	 */
	int chosenPath;
	
	private static final int STAYING = -1;
	private static final int TURNAROUND = -2;
	
	public Boolean isStaying() {
		return chosenPath==STAYING;
	}
	public Boolean wantsToTurn() {
		return chosenPath==TURNAROUND;
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

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		if (Settings.isDebugOutputEnabled) {
			System.out.println("Client:" + getID() + " started!");
		}
	}
	
	

}
