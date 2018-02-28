/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import controls.Client;
import controls.Session;
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
	
	public Game(Session s) {
		clients = s.getClients();
		
	}

	static Game createSimpleTestGame() {
		Session s = new Session(2);
		Game r = new Game(s);
		
		return r;
	}
		
	
	
}
