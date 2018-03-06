
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
		System.out.println("\n------------------------");
		System.out.print("Welcome to LogisticArena");
		for (int i = 0; i < args.length; i++) {
			System.out.print(" "+args[i]);
			if(args[i].equals("-debug"))
				Settings.isDebugOutputEnabled = true ;
		}
		System.out.println("\n------------------------");
		Session s = new Session("consolesolo");
		s.runSimpleTestGames(1);
		s.end();
	}
}