/**
 * 
 */
package main;

import controls.Session;
import gamecore.Game;
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
		createSimpleTestGame();
	}
	
	static Game createSimpleTestGame() {
		Session s = new Session(2);
		Graph g = new Graph();
		Game r = new Game(s, g);
		
		return r;
	}

}