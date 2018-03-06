/**
 * 
 */
package controls;

import java.util.ArrayList;

import controls.bots.HamsterBot;
import controls.bots.RabbitBot;
import controls.console.ConsolePlayer;
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
		switch (s.toLowerCase()) {
		case "botduel":
			clients.add(new HamsterBot(0));
			clients.add(new RabbitBot(1));
			break;	
		case "botffa":
			for (int i = 0; i < 8; i++) {
				if (Math.random() < 0.5) {
					clients.add(new HamsterBot(i));
				} else {
					clients.add(new RabbitBot(i));
			}
		}
			break;
			
		case "consolesingle":
			clients.add(new ConsolePlayer(0));
			clients.add(new HamsterBot(1));
			break;
			
		default:
			break;
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
