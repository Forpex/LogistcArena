/**
 * 
 */
package controls.bots;

import gamecore.graph.Graph;

/**
 * This is an Automated Opponent.
 * Chooses Destinations randomly and should not stand still.
 * @author Andreas Stock
 *
 */
public class RabbitBot extends Bot {

	private static final int RUNAWAY_DISTANCE = 6;

	public RabbitBot(int id) {
		super(id);
		setName("RabbitBot");
	}

	synchronized void decide() {
		if (intel != null) {
			if (this.getDestination() == null ) {
				setDestination(intel.graph.getASpawnPoint(Graph.extractAvatarPositions(intel.visibleEnemyAvatars), RUNAWAY_DISTANCE));
			}
		} 
	}
}
