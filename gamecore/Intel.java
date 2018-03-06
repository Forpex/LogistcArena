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
	
	public Avatar self;
	public ArrayList<Avatar> visibleEnemyAvatars = new ArrayList<Avatar>(0);
	public Graph graph;
	public ArrayList<Item> visibleItems = new ArrayList<Item>(0);
	Score currentScore;
	public Time currentTime;
	Settings settings;
	public boolean isGameOver;
	
	Intel(Game g, Avatar self){
		this.self = self;
		visibleEnemyAvatars = g.getVisibleAvatars(self);
		this.graph = g.graph;
		this.visibleItems = g.graph.getItemsInSight(self.getPosition());
		this.currentScore = g.score.clone();
		this.currentTime = g.currentTime.clone();
		this.settings = g.settings;
		this.isGameOver = g.isGameOver;
	}
	
}
