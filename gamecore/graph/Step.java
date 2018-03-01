/**
 * 
 */
package gamecore.graph;

import gfx.Displayable;

/**
 * @author Andreas Stock
 *
 */
public class Step implements Position, Displayable {
	
	Edge mother;
	Position next;
	Position previous;
	public final Boolean isBidirectional = false;
	
	Step(Edge mother, Position next, Position previous) {
		super();
		this.mother = mother;
		this.next = next;
		this.previous = previous;
	}

	/**
	 * @return the previous
	 */
	Position previous() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	void setPrevious(Position previous) {
		this.previous = previous;
	}

	/**
	 * @param next the next to set
	 */
	void setNext(Position next) {
		this.next = next;
	}

	/* (non-Javadoc)
	 * @see graph.Position#distance(graph.Avatar, java.lang.Boolean)
	 */
	@Override
	public int distance(Position p, Boolean justIntel) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see graph.Position#next()
	 */
	@Override
	public Position next() {
		return next;
		
	}

	/* (non-Javadoc)
	 * @see graph.Position#next(graph.Edge)
	 */
	@Override
	public Position next(int chosenPathID) {
		return next;
	}

	/* (non-Javadoc)
	 * @see gamecore.graph.Position#getNumPathChoices()
	 */
	@Override
	public int getNumPathChoices() {
		return 0;
	}

}
