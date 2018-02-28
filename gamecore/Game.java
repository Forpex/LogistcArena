/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import controls.Client;
import gamecore.graph.Graph;

/**
 * @author Andreas Stock
 *
 */
public class Game {

	ArrayList<Avatar> avatars;
	ArrayList<Client> clients;
	
	Graph graph;
	
	ArrayList<Item> items;
	
	Score currentScore = new Score(avatars.size());
	
	Time currentTime;
	
	
	
	Settings settings;
		
	
	
}
