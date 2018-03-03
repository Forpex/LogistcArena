/**
 * 
 */
package controls;

import gamecore.Intel;

/**
 * This is an Automated Opponent.
 * In this early Version it chooses paths randomly and does not stand still.
 * @author Andreas Stock
 *
 */
public class RandomBot extends Client {

	public RandomBot(int id) {
		super("RandomBot",id);
	}
	
	public synchronized void post(Intel intel) {
		super.lastIntelGotten = intel;
		decide();
	}

	private synchronized void decide() {
		if (super.lastIntelGotten == null) {
			super.chosenPath = 0;
		} else {
			super.chosenPath = (int) Math.random() * super.lastIntelGotten.getNumOutgoingEdges();
		}
	}
}
