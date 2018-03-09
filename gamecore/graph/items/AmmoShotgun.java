package gamecore.graph.items;

import gamecore.graph.Position;

public class AmmoShotgun extends Ammo {

	public AmmoShotgun(Position p) {
		super(p);
	}

	public String getName() {
		return "shotgun";
	}

}
