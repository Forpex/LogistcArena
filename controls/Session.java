/**
 * 
 */
package controls;

import java.util.ArrayList;

import gamecore.Game;
import gamecore.Score;
import gamecore.graph.Graph;
import gamecore.graph.items.Item;

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
	public Session(int numberOfBots) {
		for (int i = 0; i < numberOfBots; i++) {
			clients.add(new RandomBot(i));
		}
	}

	public int size() {
		
		return clients.size();
	}

	public void runSimpleTestGames(int numgames) {
		Score totalScore = new Score(this.size());
		for (int i = 0; i < numgames; i++) {
			Graph graph = new Graph(Item.TOTAL_NUMBER_OF_ITEM_TYPES+2, Math.random());
			Game g = new Game(this, graph);
			g.start();
			g.join();
			totalScore.add(g.getScore());
		}
		System.out.println("TotalScore of "+numgames+" games is " +totalScore);
		
	}
	
	
}
