/**
 * 
 */
package gamecore.graph.items;

import gamecore.Settings;
import gamecore.avatars.Avatar;
import gamecore.graph.Position;

/**
 * @author Andreas Stock
 *
 */
public class ItemMegaHealth extends Item {

	public ItemMegaHealth(Position p) {
		super(p, 35);
	}

	@Override
	public void applyBonusTo(Avatar a) {
		a.setHealth(Math.min(a.getHealth()+100,Settings.MAX_HEALTH));		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MegaHealth";
	}
	
	
	
}
