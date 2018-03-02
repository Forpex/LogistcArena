/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import gamecore.graph.Graph;
import gamecore.graph.items.Item;

/**
 * @author Andreas Stock
 *
 */
public class Intel {
	
	Avatar self;
	
	ArrayList<Avatar> visibleEnemyAvatars;
	
	Graph graph;
	
	ArrayList<Item> visibleItems;
	
	Score currentScore;
	
	Time currentTime;
	Settings settings;
	
	
	
	Intel(Avatar self, ArrayList<Avatar> enemyAvatars, Graph graph, ArrayList<Item> visibleItems, Score currentScore,
			Time currentTime, Settings settings) {
		super();
		this.self = self;
		this.visibleEnemyAvatars = enemyAvatars;
		this.graph = graph;
		this.visibleItems = visibleItems;
		this.currentScore = currentScore;
		this.currentTime = currentTime;
		this.settings = settings;
	}



	public int getNumPathChoices() {
		return self.position.getNumPathChoices();
	}
	
}
