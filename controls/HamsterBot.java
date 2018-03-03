/**
 * 
 */
package controls;

import gamecore.graph.items.Item;

/**
 * This is an Automated Opponent.
 * Moves towards nearest visible Item.
 * If there is no item or the waiting time would be too long, 
 * it chooses Destinations randomly.
 * @author Andreas Stock
 *
 */
public class HamsterBot extends Bot {

	private static final int TOO_MUCH_WAITING = 10;

	/**
	 * @param id
	 */
	public HamsterBot(int id) {
		super(id);
	}

	/* (non-Javadoc)
	 * @see controls.Bot#decide()
	 */
	@Override
	void decide() {
		if (getDestination() == null ) {
			int min = Integer.MAX_VALUE;
			if (lastIntelGotten.visibleItems.size() != 0)  {
				for (Item item : lastIntelGotten.visibleItems) {
					int waitTimeOnArrival = item.getTimeLeftToRespawn()
							-item.getPosition().distance(lastIntelGotten.self.getPosition(), true);
					if (waitTimeOnArrival < min ) {
						min = waitTimeOnArrival;
						setDestination(item.getPosition());
					}
				}
			}
			if (min >= TOO_MUCH_WAITING){
				setDestination(lastIntelGotten.graph.getAllPositions().get((int) Math.random() * lastIntelGotten.graph.getAllPositions().size()));
			} 
		} 
	}

}
