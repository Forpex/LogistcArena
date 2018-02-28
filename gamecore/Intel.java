/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import gamecore.graph.Graph;

/**
 * @author Andreas Stock
 *
 */
public class Intel {
	
	ArrayList<Avatar> avatars;
	
	Graph graph;
	
	ArrayList<Item> items;
	
	Score currentScore = new Score(avatars.size());
	
	Time currentTime;
	Time endTime;
	
}
