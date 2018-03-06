/**
 * 
 */
package controls;

import gamecore.Intel;
import gamecore.Settings;
import gamecore.graph.Location;

/**
 * @author Andreas Stock
 *
 * Controls: Keyboard, RabbitBot, etc.
 */
public abstract class Player extends Thread{

	int id;

	protected Intel intel;
	
	private Location destination;

	public Player(int id) {
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
			System.out.println("Player:" + getID() + " started!");
		}
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}
	
	

}
