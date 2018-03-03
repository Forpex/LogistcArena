/**
 * 
 */
package controls;

import gamecore.Intel;
import gamecore.Settings;
import gamecore.graph.Position;

/**
 * @author Andreas Stock
 *
 * Controls: Keyboard, RabbitBot, etc.
 */
public abstract class Client extends Thread{

	int id;

	Intel lastIntelGotten;
	
	Position destination;

	public Client(int id) {
		super();
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
