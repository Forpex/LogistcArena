/**
 * 
 */
package gamecore.avatars;

import java.util.ArrayList;

import controls.Client;
import gamecore.Settings;
import gamecore.graph.Position;
import gfx.Displayable;

/**
 * @author Andreas Stock
 *
 */
public class Avatar implements Displayable{

	Position position;

	//properties
	private int health = Settings.START_HEALTH;
	int armor = Settings.START_ARMOR;
	ArrayList<Weapon> weapons = Weapon.allWeapons();
	private Client client;
	
	public Boolean isAlive() {
		return health >= 0;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	
	public Avatar(Client client, Position startingPointOnGraph) {
		this.client = client;
		this.position = startingPointOnGraph;
	}

	/**
	 * @param damageAmount
	 * @param sourceID id of client controling the avatar. if id == -1 its the world killing it!
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
			die(sourceID);
		}
	}

	private void die(int sourceID) {
		if (Settings.isDebugOutputEnabled) {
			System.out.print("Avatar of Client #" + client.getID());
			if (sourceID >= 0) {
				System.out.println(" gotKilled by Avatar of Client #" + sourceID);
			} else {
				System.out.println(" died!");
			}
			
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
	
	
	
}
