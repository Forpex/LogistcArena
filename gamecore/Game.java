/**
 * 
 */
package gamecore;

import java.util.ArrayList;
import java.util.Iterator;

import controls.Client;
import controls.Session;
import gamecore.graph.Graph;
import gamecore.graph.Item;
import gamecore.graph.Position;

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
	
	Boolean isGameOver = false;
	
	public Game(Session s, Graph g) {

		this.graph = g;
		this.items = g.getItems();
		
		this.clients = s.getClients();
		
		this.avatars = generateAvatars(this.clients,g);
		
		this.currentScore = new Score(avatars.size());
		this.currentTime = new Time(0);
		this.settings = new Settings();
	}

	private ArrayList<Avatar> generateAvatars(ArrayList<Client> clients, Graph g) {
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Client client : clients) {
			r.add(new Avatar(this, client, g.getRandomPlayerSpawnPoint()));
		}
		return r;
	}

	
	public ArrayList<Avatar> getAvatarsInPosition(Position p){
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Avatar avatar : avatars) {
			if (p == avatar.getPosition()
					|| p == avatar.getPosition().turn()) {
				r.add(avatar);
			}
		}
		return r;
	}
	
}
