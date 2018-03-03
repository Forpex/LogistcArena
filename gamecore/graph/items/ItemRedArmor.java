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
public class ItemRedArmor extends Item {

	/**
	 * @param p
	 * @param secondsForRespawnCycle
	 */
	public ItemRedArmor(Position p) {
		super(p, 25);
	}

	/* (non-Javadoc)
	 * @see gamecore.graph.items.Item#applyBonusTo(gamecore.avatars.Avatar)
	 */
	@Override
	public void applyBonusTo(Avatar a) {
		a.setArmor(Math.min(a.getArmor()+Settings.ARMOR_BLEED_ABOVE,Settings.MAX_ARMOR));	

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RedArmor";
	}
	
}
