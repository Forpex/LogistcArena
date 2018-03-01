
import controls.Session;
import gamecore.Game;
import gamecore.Item;
import gamecore.Settings;
import gamecore.graph.Graph;

/**
 * @author Andreas Stock
 *
 */
public class LogisticArena {

	/**
	 * without any arguments, it starts a singleplayer game against a bot on the default testMap.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("-------------------\nWelcome to LogisticArena\n-------------------");
		//stub
		Settings.isDebugOutputEnabled = true;
		Game game = createSimpleTestGame();
		//game.start(); TODO
	}
	
	static Game createSimpleTestGame() {
		Session s = new Session(2);
		Graph g = new Graph(Item.length+1, 0.75);
		Game r = new Game(s, g);
		
		return r;
	}

}