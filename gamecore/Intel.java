/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import gamecore.avatars.Avatar;
import gamecore.graph.Graph;
import gamecore.graph.items.Item;

/**
 * @author Andreas Stock
 *
 */
public class Intel {
	
	Avatar self;
	ArrayList<Avatar> visibleEnemyAvatars = new ArrayList<Avatar>(0);
	Graph graph;
	ArrayList<Item> visibleItems = new ArrayList<Item>(0);
	Score currentScore;
	Time currentTime;
	Settings settings;
	
	Intel(Game g, Avatar self){
		this.self = self;
		for (Avatar a : g.avatars) {
			if (a != self 
					&& a.isAlive()
					&& a.getPosition().distance(self.getPosition(), true)<=Settings.INTEL_DISTANCE) {
				visibleEnemyAvatars.add(a);
			}
		}
		this.graph = g.graph;
		this.visibleItems = g.graph.getItemsInSight(self.getPosition());
		this.currentScore = g.currentScore.clone();
		this.currentTime = g.currentTime.clone();
		this.settings = g.settings;
	}

	public int getNumPathChoices() {
		return self.getPosition().getNumPathChoices();
	}
	
}
