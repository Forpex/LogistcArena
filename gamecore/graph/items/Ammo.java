/**
 * 
 */
package gamecore.graph.items;

import gamecore.Settings;
import gamecore.avatars.Avatar;
import gamecore.graph.Position;

/**
 * ammo should fill up an avatar's weapon by some amount.
 * @author aeypex
 *
 */
public abstract class Ammo extends Item {


	public Ammo(Position p) {
		super(p, Settings.WEAPON_RESPAWN_TIMER);

	}

	public void applyBonusTo(Avatar a) {

		a.getWeaponByName(this.getName()).addAmmo(Settings.AMMO_AMOUNT);
	}

	public abstract String getName();

}