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
	
	
	public void post(Intel intel) {
		super.lastIntelGotten = intel;
		
		decide();
	}


	private void decide() {
		super.chosenPath = (int) Math.round((Math.random() * super.lastIntelGotten.getNumPathChoices()));
	}
	
	
}
