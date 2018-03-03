/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import controls.Client;
import controls.Session;
import gamecore.avatars.Avatar;
import gamecore.graph.Graph;
import gamecore.graph.Position;
import gamecore.graph.items.Item;

/**
 * @author Andreas Stock
 *
 */
public class Game extends Thread{
	Settings settings;
	ArrayList<Client> clients;	
	ArrayList<Avatar> avatars;	
	Graph graph;	
	Score currentScore;	
	Time currentTime;	
	Boolean isGameOver = false;
	GameStateIterator iterator;
	
	public Game(Session s, Graph g) {
		super();
		this.settings = new Settings();
		this.graph = g;
		this.clients = s.getClients();
		this.avatars = generateAvatars(this.clients,g);
		this.currentScore = new Score(avatars.size());
		this.currentTime = new Time(0);
		this.iterator = new GameStateIterator(this);
	}

	private static ArrayList<Avatar> generateAvatars(ArrayList<Client> clients, Graph g) {
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Client client : clients) {
			r.add(new Avatar(client, g.getPlayerSpawnPoint()));
		}
		return r;
	}

	public ArrayList<Avatar> getAvatarsInPosition(Position p){
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Avatar avatar : avatars) {
			if (avatar.getPosition().distance(p, false) == 0)
			/*(p == avatar.getPosition()
					|| p == avatar.getPosition().turn())*/
			{
				r.add(avatar);
			}
		}
		return r;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		for (Client c : clients) {
			c.start();
		}
		
		iterator.start();
	}

	synchronized void iterateGamestate() {
		if (!isGameOver) {
			for (int i = 0; i < avatars.size(); i++) {
				Avatar a = avatars.get(i);
				System.out.println(a);
				a.iterate(this);
				//check for kills
				if (!a.isAlive()) {
					currentScore.increment(a.getPossibleKiller());
					//respawn
					respawn(a);
				}
			}
			for (Item i : graph.getItems()) {
				i.iterate(this);
			} 
			// round finished: move time!
			currentTime.increment();
			//post status to console
			System.out.println(currentTime.toString(this) + " --> " + currentScore);
		}
		
		//set gameover if time out
		if (currentTime.equals(settings.gamelength)) {
			isGameOver = true;
			System.out.println("----------------\nGameOver --> " + currentScore);
		}
		
		//post intel to clients
		for (Avatar a : avatars) {
			a.getClient().post(new Intel(this,a));
		}
		
		
	}

	public void respawn(Avatar a) {
		int index = avatars.indexOf(a);
		avatars.remove(a);
		avatars.add(index, new Avatar(a.getClient(), graph.getPlayerSpawnPoint()));
	}

	public ArrayList<Avatar> getVisibleAvatars(Avatar self) {
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Avatar a : avatars) {
			if (a != self 
					&& a.isAlive()
					&& a.getPosition().distance(self.getPosition(), true) <= Settings.INTEL_DISTANCE) {
				r.add(a);
			}
		}
		return r;
	}
	
	
	
}
