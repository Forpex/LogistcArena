/**
 * 
 */
package gamecore.avatars;

import java.util.ArrayList;

import controls.Player;
import gamecore.Game;
import gamecore.Settings;
import gamecore.graph.Position;

/**
 * @author Andreas Stock
 *
 */
public class Avatar{

	Position position;

	//properties
	private int health = Settings.START_HEALTH;
	private int armor = Settings.START_ARMOR;
	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}

	/**
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}

	ArrayList<Weapon> weapons;
	private Player client;

	private int possibleKiller;
	
	public Boolean isAlive() {
		return health >= 0;
	}

	/**
	 * @return the client
	 */
	public Player getClient() {
		return client;
	}

	
	public Avatar(Player client, Position startingPointOnGraph) {
		this.client = client;
		this.position = startingPointOnGraph;
		this.weapons = Weapon.generateNewWeapons();
	}

	/**
	 * @param damageAmount
	 * @param sourceID id of client controling the avatar. if id == -1 its the world, killing it!
	 */
	public void getDamaged(int damageAmount, int sourceID) {
		//int damageToBeDone = damageAmount;
		int armorDamage = damageAmount / 3 * 2;
		armor -= armorDamage; //apply 2/3 of damage to armor
		setHealth(getHealth() - (damageAmount - armorDamage)); //apply 1/3 to health
		if (armor < 0) {
			setHealth(getHealth() + armor); //carry over damage from armor to heath if armor is not sufficient.
			armor = 0;
		}
		
		
		if (getHealth()<=0) {
			possibleKiller = sourceID;
		}
	}

	public int distanceTo(Avatar receiver) {
		
		return this.position.distance(receiver.position, false);
	}

	/**
	 * @return the position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void iterate(Game game) {
		if (isAlive()) {
			iteratePosition();
			iterateBleed();
			iterateShots(game);
		}
	}

	private void iterateShots(Game game) {
		ArrayList<Avatar> targets = game.getVisibleAvatars(this);
		for (Avatar a : targets) {
			int distance = this.position.distance(a.getPosition(), false);
			int i = 0;
			while (i < weapons.size()-1 && !weapons.get(i).canShoot(distance)){
				i++;
			}
			weapons.get(i).damageAvatar(a, this);
		}
	}

	private void iterateBleed() {
		if (armor > Settings.ARMOR_BLEED_ABOVE) {
			armor --;
		}
		if (health > Settings.HEALTH_BLEED_ABOVE) {
			health --;
		}
	}

	private void iteratePosition() {
		if (this.getClient().getDestination() != null) {
			this.position = this.position.next(this.getClient().getDestination());
		}
	}

	public int getPossibleKiller() {
		return possibleKiller;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String r = "Avatar#"+this.getClient().getID()+"" +" --> Pos="+this.position+ " Life="+this.health+" Armor="+this.armor;
		for (Weapon weapon : weapons) {
			r += " "+weapon;
		}
		return r;
	}
	
	public Weapon getWeaponByName(String name) {
		Weapon r = null;
		for (Weapon weapon : weapons) {
			if (weapon.getName() == name.toUpperCase()) {
				return weapon;
			}
		}
		return r;
	}
	
}
