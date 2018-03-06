package gamecore.avatars;

import java.util.ArrayList;

import gamecore.Settings;

public class Weapon {
	
	/* I had this as an enum first, but these entities are only static and therefore ammo was shared :(
	 * SHOTGUN (Settings.SHOTGUN_DPS , Settings.SHOTGUN_RANGE, 0),
	LIGHTNINGGUN (Settings.LIGHTNING_DPS , Settings.LIGHTNING_RANGE, 0),
	RAILGUN (Settings.RAILGUN_DPS , Settings.RAILGUN_RANGE, 0),
	PISTOL (Settings.PISTOL_DPS , Settings.PISTOL_RANGE, -1 );*/
	
	int dps = 10;
	int range = 10;
	int ammo = Integer.MAX_VALUE;
	private String name;
	
	Weapon(String name, int dps, int range, int ammo) {
			this.setName(name);
			this.dps = dps;
			this.range = range;
			this.ammo = ammo;	
	}
	
	
	void damageAvatar(Avatar receiver, Avatar dealer) {
		if (canShoot(dealer.distanceTo(receiver))) {
			if(ammo > 0)  ammo-- ;
			receiver.receiveDamage(dps, dealer.getClient().getID());
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
	
	public static int length = Weapon.generateNewWeapons().size();
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		String r = "";
		if (!name.toLowerCase().equals("pistol")) {
			r += ammo;
		}
		while (r.length() < 2) {
			r+= " ";
		}
		r+=" "+ getName();
		while (r.length() < 13) {
			r+= " ";
		}
		r+=" (Stats: r="+range+", dps="+dps+")";
		return r;
	}


	public static ArrayList<Weapon> generateNewWeapons() {
		ArrayList<Weapon> r = new ArrayList<Weapon>(0);
		r.add(new Weapon("Shotgun",Settings.SHOTGUN_DPS , Settings.SHOTGUN_RANGE, 0));
		r.add(new Weapon("Lightning" ,Settings.LIGHTNING_DPS , Settings.LIGHTNING_RANGE, 0));
		r.add(new Weapon("Railgun", Settings.RAILGUN_DPS , Settings.RAILGUN_RANGE, 0));
		r.add(new Weapon("Pistol", Settings.PISTOL_DPS , Settings.PISTOL_RANGE, -1 ));
		return r;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
