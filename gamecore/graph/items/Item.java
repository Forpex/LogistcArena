package gamecore.graph.items;



import java.util.ArrayList;

import gamecore.Game;
import gamecore.Settings;
import gamecore.Time;
import gamecore.avatars.Avatar;
import gamecore.graph.Position;
import gfx.Displayable;


/**
 * These Items will be distributed around the graph and can be picked up by an avatar to gain its bonus.
 * @author Andreas Stock
 *
 */
public abstract class Item implements Displayable {

	Time timeRespawnCycle;
	Time timeLeftToRespawn;
	
	/**
	 * attentione: only for bots to cheat hahaha
	 * @return the timeLeftToRespawn
	 */
	public int getTimeLeftToRespawn() {
		return timeLeftToRespawn.getSeconds();
	}

	Position position; 									//==some node.
		
	public Item( Position p, int secondsForRespawnCycle) {
		this.position = p;
		this.timeRespawnCycle = new Time(secondsForRespawnCycle);
		this.timeLeftToRespawn = new Time(0); 			/*item is available from the start */
	}
	
	public void iterate(Game game) {
		timeLeftToRespawn.decrement();

		ArrayList<Avatar> avatarsAtItem = game.getAvatarsInPosition(this.position);
		if (avatarsAtItem.size() != 0
				&& isPickupable()) {
			pickup(avatarsAtItem);
		}
	}
	
	public Boolean isPickupable() {
		Boolean r = false;
			r = (timeLeftToRespawn.getSeconds() <= 0);
		return r;
	}
	
	/**
	 * detects players on the same position
	 * then applies the bonus.
	 * gives item to random avatar if there are multiple
	 * restarts respawntimer
	 * @param game 
	 */
	public void pickup(ArrayList<Avatar> avatarsAtItem) {
		Avatar a = avatarsAtItem.get((int)(Math.random()*avatarsAtItem.size()));
		if (Settings.isDebugOutputEnabled) {
			System.out.println("Pickup: " + this + " by Avatar " + a.toString().substring(0, 8));
		}
		applyBonusTo(a);
		restartRespawnCycle();
	}

	private void restartRespawnCycle() {
		timeLeftToRespawn = timeRespawnCycle.clone();
	}
	
	public abstract void applyBonusTo(Avatar a);

	public Position getPosition() {
		return position;
	}
	
	
	
}
