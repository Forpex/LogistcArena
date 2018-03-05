/**
 * 
 */
package controls;

import gamecore.Intel;
import gamecore.graph.Graph;

/**
 * This is just simply to Test. Inputs will be done with console and debugging outputs.
 * 
 * @author Andreas Stock
 *
 */
public class ConsolePlayer extends Player {

	public ConsolePlayer(int id) {
		super(id);
	}

	@Override
	public void post(Intel intel) {
		System.out.println("IntelGotten: \n"+intel.toString());
		do {
			//draw graph in ascii art
			// ^^ need relative node positions for this first.
			
			//scanner and input
			
			//pick node that corresponds to input id
			
			super.setDestination(intel.graph.getASpawnPoint(Graph.extractAvatarPositions(intel.visibleEnemyAvatars), 3));
		} while (!intel.isGameOver || super.getDestination() == null);
	}

}
