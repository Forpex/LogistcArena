package gamecore;



import gamecore.graph.Position;
import gfx.Displayable;


/**
 * These Items will be distributed around the graph and can be picked up by an avatar to gain its bonus.
 * @author Andreas Stock
 *
 */
public abstract class Item implements Displayable {

	int timeRespawnCycle;
	int timeLeftToRespawn = 0;
	
	Position location; //==some node.
	
	public Item(Position p, int timeRespawnCycle) {
		this.location = p;
		this. timeRespawnCycle = timeRespawnCycle;
	}
	
	public Boolean isPickupable() {
		Boolean r = false;
		r = (timeLeftToRespawn <= 0);
		return r;
	}
	
	/**
	 * detects players on the same location
	 * then applies the bonus.
	 * checks for multiple players on the field.
	 * restarts respawntimer
	 */
	public void pickup() {
		if (location.avatars.size() == 1) {
			applyBonusTo(location.avatars.get(0));
			restartRespawnCycle();
		}
		
	}

	private void restartRespawnCycle() {
		timeLeftToRespawn = timeRespawnCycle;
	}
	
	public abstract void applyBonusTo(Avatar a);

}
