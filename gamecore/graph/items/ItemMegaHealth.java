/**
 * 
 */
package gamecore.graph.items;

import gamecore.Avatar;
import gamecore.Game;
import gamecore.Settings;
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
	
}
