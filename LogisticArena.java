
import controls.Session;
import gamecore.Settings;

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
		Session s = new Session("botduel");
		s.runSimpleTestGames(10);
		s.end();
	}
}