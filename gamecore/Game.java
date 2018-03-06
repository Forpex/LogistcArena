/**
 * 
 */
package gamecore;

import java.util.ArrayList;

import controls.Player;
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
	ArrayList<Player> clients;	
	ArrayList<Avatar> avatars;	
	Graph graph;	
	Score score;	
	/**
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	Time currentTime;	
	Boolean isGameOver = false;
	
	public Game(Session s, Graph g) {
		super();
		this.settings = new Settings();
		this.graph = g;
		this.clients = s.getClients();
		this.avatars = generateAvatars(this.clients,g);
		this.score = new Score(avatars.size());
		this.currentTime = new Time(0);
		
		if (Settings.isDebugOutputEnabled) 
			System.out.println("\n"+"New Game Started --> " + score + "\n----------------");
	}

	private static ArrayList<Avatar> generateAvatars(ArrayList<Player> clients, Graph g) {
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		ArrayList<Position> alreadySpawnedAtPositions = new ArrayList<Position>(0);
		for (Player client : clients) {
			Position playerSpawnPoint = g.getASpawnPoint(alreadySpawnedAtPositions, Settings.MINIMAL_SPAWN_DISTANCE);
			alreadySpawnedAtPositions.add(playerSpawnPoint);
			r.add(new Avatar(client, playerSpawnPoint));
		}
		return r;
	}

	public ArrayList<Avatar> getAvatarsInPosition(Position p){
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Avatar avatar : avatars) {
			if (avatar.getPosition().distance(p) == 0)
			/*(p == avatar.getPosition()
					|| p == avatar.getPosition().turn())*/
			{
				r.add(avatar);
			}
		}
		return r;
	}
	
	/**
	 * this is no thread anymore
	 */
	public void run() {
		while (!isGameOver) {
			iterateGamestate();
			try {
				sleep((long) (1000 / settings.timescale));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.interrupt();
	}

	synchronized void iterateGamestate() {
		//set gameover if time out
		if (currentTime.equals(settings.gamelength)) {
			isGameOver = true;
		}
		
		
		if (!isGameOver) {
			for (int i = 0; i < avatars.size(); i++) {
				Avatar a = avatars.get(i);
				
				a.iterate(this);
				//check for kills
				if (!a.isAlive()) {
					score.increment(a.getPossibleKiller());
					if( Settings.isDebugOutputEnabled) {
						System.out.print("Avatar of Player #" + a.getClient().getID());
						if (a.getPossibleKiller() >= 0) {
							System.out.println(" gotKilled by Avatar of Player #" + a.getPossibleKiller());
						} else {
							System.out.println(" just died!");
						}
					}
					//respawn
					respawn(a);
				}
			}
			for (Item i : graph.getItems()) {
				i.iterate(this);
			} 
			// round finished: move time!
			currentTime.increment();
		}
		
		//post intel to clients
		for (Avatar a : avatars) {
			a.getClient().post(new Intel(this,a));
		}
		
		if (isGameOver) {
			handleGameOver();
		}
		
	}

	public void handleGameOver() {
		isGameOver = true;
		for (Player client : clients) {
				client.interrupt();
		}
		if (Settings.isDebugOutputEnabled) 
			System.out.println("----------------\nGameOver --> " + score+ "\n\n\n");
	}

	public void respawn(Avatar a) {
		int index = avatars.indexOf(a);
		avatars.remove(a);
		
		ArrayList<Position> avatarPositions = Graph.extractAvatarPositions(avatars);
		Position playerSpawnPoint = this.graph.getASpawnPoint(avatarPositions, Settings.MINIMAL_SPAWN_DISTANCE);
		avatars.add(index, new Avatar(a.getClient(), playerSpawnPoint));
	}

	

	public ArrayList<Avatar> getVisibleAvatars(Avatar self) {
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		for (Avatar a : avatars) {
			if (a != self 
					&& a.isAlive()
					&& a.getPosition().distance(self.getPosition()) <= Settings.INTEL_DISTANCE) {
				r.add(a);
			}
		}
		return r;
	}

	
	
	
	
}
