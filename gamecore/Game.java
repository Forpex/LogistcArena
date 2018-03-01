/**
 * 
 */
package gamecore;

import java.util.ArrayList;
import java.util.Iterator;

import controls.Client;
import controls.Session;
import gamecore.graph.Graph;

/**
 * @author Andreas Stock
 *
 */
public class Game {

	ArrayList<Client> clients;
	
	ArrayList<Avatar> avatars;
	
	Graph graph;
	
	ArrayList<Item> items;
	
	Score currentScore;
	
	Time currentTime;
	
	Settings settings;
	
	public Game(Session s, Graph g) {

		this.graph = g;
		this.items = g.allItems();
		
		this.clients = s.getClients();
		
		for (Client client : clients) {
			this.avatars.add(new Avatar(this, client,g.getRandomPlayerSpawnPoint()));
		}
		
		this.currentScore = new Score(avatars.size());
		this.currentTime = new Time(0);
		this.settings = new Settings();
	}

	

	
		
	
	
}
