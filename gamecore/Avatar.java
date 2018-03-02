/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import controls.Client;
import gamecore.graph.Position;
import gfx.Displayable;

/**
 * @author Andreas Stock
 *
 */
public class Avatar implements Displayable{

	Position position;

	//properties
	int health = Settings.START_HEALTH;
	int armor = Settings.START_ARMOR;
	ArrayList<Weapon> weapons = Weapon.allWeapons();
	
	Client client;

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	private Game game;
	
	public Avatar(Game game, Client client, Position startingPointOnGraph) {
		this.client = client;
		this.position = startingPointOnGraph;
		this.game = game;
	}

	/**
	 * @param damageAmount
	 * @param sourceID id of client controling the avatar. if id == -1 its the world killing it!
	 */
	public void getDamaged(int damageAmount, int sourceID) {
		//int damageToBeDone = damageAmount;
		int armorDamage = damageAmount / 3 * 2;
		armor -= armorDamage; //apply 2/3 of damage to armor
		health -= damageAmount - armorDamage; //apply 1/3 to health
		if (armor < 0) {
			health += armor; //carry over damage from armor to heath if armor is not sufficient.
			armor = 0;
		}
		
		
		if (health<=0) {
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
	
	
	
}
