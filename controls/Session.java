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
	ArrayList<Player> clients = new ArrayList<Player>(0);

	/**
	 * @return the clients
	 */
	public ArrayList<Player> getClients() {
		return clients;
	}

	public Session(ArrayList<Player> clients) {
		super();
		this.clients = clients;
		for (Player c : clients) {
			c.start();
		}
	}

	public void end() {
		for (Player c : clients) {
			c.interrupt();
		}
	}

	/**
	 * Starts a Session with Bots only
	 * @param i
	 */
	public Session(String s) {
		if (s.toLowerCase() == "botduel") {
			clients.add(new HamsterBot(0));
			clients.add(new RabbitBot(1));
		}
		if (s.toLowerCase() == "botffa") {
			for (int i = 0; i < 8; i++) {
				
				if (Math.random() < 0.5) {
					clients.add(new HamsterBot(i));
				} else {
					clients.add(new RabbitBot(i));
				}
			}
		}
	}

	public int size() {
		
		return clients.size();
	}

	public void runSimpleTestGames(int numgames) {
		Score totalScore = new Score(this.size());
		for (int i = 0; i < numgames; i++) {
			Graph graph = new Graph(Settings.TOTAL_NUMBER_OF_ITEM_TYPES+3, 0.5);
			Game g = new Game(this, graph);
			g.start();
			try {
				g.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			totalScore.add(g.getScore());
		}
		System.out.println("TotalScore of "+numgames+" games is " +totalScore);
		
	}
	
	
}
