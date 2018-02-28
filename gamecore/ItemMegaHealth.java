/**
 * 
 */
package gamecore;

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
		a.health = Math.min(a.health+100,Settings.MAXHEALTH);		
	}

	
	
}
