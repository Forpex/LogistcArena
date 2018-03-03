/**
 * 
 */
package controls;

import java.util.ArrayList;

import gamecore.Game;
import gamecore.Score;
import gamecore.Settings;
import gamecore.graph.Graph;

/**
 * Contains multiple Clients
 * This is meant to handle the MultiplayerLobby later.
 * 
 * @author Andreas Stock
 *
 */
public class Session {
	ArrayList<Client> clients = new ArrayList<Client>(0);

	/**
	 * @return the clients
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

	public Session(ArrayList<Client> clients) {
		super();
		this.clients = clients;
		for (Client c : clients) {
			c.start();
		}
	}

	public void end() {
		for (Client c : clients) {
			c.interrupt();
		}
	}

	/**
	 * Starts a Session with Bots only
	 * @param i
	 */
	public Session(String s) {
		if (s == "botduel") {
			clients.add(new RabbitBot(0));
			clients.add(new HamsterBot(1));
		}
	}

	public int size() {
		
		return clients.size();
	}

	public void runSimpleTestGames(int numgames) {
		Score totalScore = new Score(this.size());
		for (int i = 0; i < numgames; i++) {
			Graph graph = new Graph(Settings.TOTAL_NUMBER_OF_ITEM_TYPES, 1);
			Game g = new Game(this, graph);
			g.start();
			g.join();
			totalScore.add(g.getScore());
		}
		System.out.println("TotalScore of "+numgames+" games is " +totalScore);
		
	}
	
	
}
