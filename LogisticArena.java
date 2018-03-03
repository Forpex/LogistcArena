
import controls.Session;
import gamecore.Game;
import gamecore.Score;
import gamecore.Settings;
import gamecore.graph.Graph;
import gamecore.graph.items.Item;

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
		Session s = new Session(2);
		s.runSimpleTestGames(3);
		s.end();
	}
	
	

}