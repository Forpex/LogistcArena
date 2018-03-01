/**
 * 
 */
package controls;

import java.util.ArrayList;

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
	
	
}
