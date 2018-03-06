/**
 * 
 */
package gamecore.graph.items;

import gamecore.Settings;
import gamecore.avatars.Avatar;
import gamecore.graph.Location;

/**
 * Provides bonus of 100Health. 
 * (Even beyond bleed threshold (normaly 100))
 * @author Andreas Stock
 *
 */
public class ItemMegaHealth extends Item {

	public ItemMegaHealth(Location p) {
		super(p, 35);
	}

	@Override
	public void applyBonusTo(Avatar a) {
		a.setHealth(Math.min(a.getHealth()+Settings.HEALTH_BLEED_ABOVE,Settings.MAX_HEALTH));		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MegaHealth";
	}
	
	
	
}
