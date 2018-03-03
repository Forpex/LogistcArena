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
public class Game{
	Settings settings;
	ArrayList<Client> clients;	
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
	GameStateIterator iterator;
	
	public Game(Session s, Graph g) {
		super();
		this.settings = new Settings();
		this.graph = g;
		this.clients = s.getClients();
		this.avatars = generateAvatars(this.clients,g);
		this.score = new Score(avatars.size());
		this.currentTime = new Time(0);
		this.iterator = new GameStateIterator(this);
		
		if (Settings.isDebugOutputEnabled) 
			System.out.println("\n\n\n"+"New Game Started --> " + score + "\n----------------");
	}

	private static ArrayList<Avatar> generateAvatars(ArrayList<Client> clients, Graph g) {
		ArrayList<Avatar> r = new ArrayList<Avatar>(0);
		ArrayList<Position> alreadySpawnedAtPositions = new ArrayList<Position>(0);
		for (Client client : clients) {
			Position playerSpawnPoint = g.getPlayerSpawnPoint(alreadySpawnedAtPositions);
			alreadySpawnedAtPositions.add(playerSpawnPoint);
			r.add(new Avatar(client, playerSpawnPoint));
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

	/** 
	 * this is no thread anymore
	 */
	public void start() {
		run();
	}
	
	/**
	 * this is no thread anymore
	 */
	public void run() {
		iterator.start();
		try {
			iterator.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * this is no thread anymore
	 */
	public void join() {
	}

	synchronized void iterateGamestate() {
		//set gameover if time out
		if (currentTime.equals(settings.gamelength)) {
			isGameOver = true;
		}
		
		
		if (!isGameOver) {
			for (int i = 0; i < avatars.size(); i++) {
				Avatar a = avatars.get(i);
				/*if ( Settings.isDebugOutputEnabled) {
					System.out.println(a);
					double distanceSum = 0;
					for (Avatar b : avatars) {
						if (a != b) {
							distanceSum += a.distanceTo(b);
						}
					}
					System.out.println("SumDist: " + distanceSum);
				}*/
				a.iterate(this);
				//check for kills
				if (!a.isAlive()) {
					score.increment(a.getPossibleKiller());
					if( Settings.isDebugOutputEnabled) {
						System.out.print("Avatar of Client #" + a.getClient().getID());
						if (a.getPossibleKiller() >= 0) {
							System.out.println(" gotKilled by Avatar of Client #" + a.getPossibleKiller());
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
			//post status to console
			/*if (Settings.isDebugOutputEnabled) 
				System.out.println(currentTime.toString(this) + " --> " + score);*/
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
		for (Client client : clients) {
			try {
				client.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (Settings.isDebugOutputEnabled) 
			System.out.println("----------------\nGameOver --> " + score+ "\n\n\n");
	}

	public void respawn(Avatar a) {
		int index = avatars.indexOf(a);
		avatars.remove(a);
		
		ArrayList<Position> avatarPositions = new ArrayList<Position>(0);
		for (Avatar enemy : avatars) {
			avatarPositions.add(enemy.getPosition());
		}
		Position playerSpawnPoint = this.graph.getPlayerSpawnPoint(avatarPositions);
		avatars.add(index, new Avatar(a.getClient(), playerSpawnPoint));
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
