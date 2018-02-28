package gamecore;

import java.util.ArrayList;

public enum Weapon {

	MASCHINEGUN (Settings.MASCHINEGUN_DPS , Integer.MAX_VALUE, Integer.MAX_VALUE ),
	RAILGUN (Settings.RAILGUN_DPS , Settings.RAILGUN_RANGE, 0),
	LIGHTNINGGUN (Settings.LIGHTNINGGUN_DPS , Settings.LIGHTNINGGUN_RANGE, 0),
	SHOTGUN (Settings.SHOTGUN_DPS , Settings.SHOTGUN_RANGE, 0);
	
	
	int dps = 10;
	int range = 10;
	int ammo = Integer.MAX_VALUE;
	
	private Weapon(int dps, int range, int ammo) {
		this.dps = dps;
		this.range = range;
		this.ammo = ammo;
	}
	
	
	void damageAvatar(Avatar receiver, Avatar dealer) {
		if (canShoot(dealer.distanceTo(receiver))) {
			ammo = (ammo > 0) ? (ammo--) : (null);
			receiver.getDamaged(dps, dealer.client.getID());
		}
	}
	
	Boolean canShoot(int distance) {
		Boolean r = false;
		
		if ((ammo > 0 || ammo < 0)
			&& range >= distance){
			r = true;
		}
		return r;
	}
	
	public static int length = Weapon.values().length;
	
	public static ArrayList<Weapon> allWeapons() {
		
		ArrayList<Weapon> r = new ArrayList<Weapon>(Weapon.length);
		for (Weapon weapon : Weapon.values()) {
			r.add(weapon);
		}
		return r;
	}
	
}
