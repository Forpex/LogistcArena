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
	Boolean isBidirectional = false;
	//Boolean forward = true;
	

	Step(Edge mother, Position next, Position previous) {
		super();
		this.mother = mother;
		this.next = next;
		this.previous = previous;
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
		/*if (forward) {
			return next;
		} else {
			return previous;
		}*/
		
	}

	/* (non-Javadoc)
	 * @see graph.Position#next(graph.Edge)
	 */
	@Override
	public Position next(int chosenPathID) {
		return next;
		/*if (chosenPathID > 0) {
			if (mother.bidirectional
					&& chosenPathID == 2) {
				forward = !forward;
			} 
			return next();
		} else {
			return this;
		}*/
	}

	/* (non-Javadoc)
	 * @see gamecore.graph.Position#getNumPathChoices()
	 */
	@Override
	public int getNumPathChoices() {
		if (mother.bidirectional) {
			return 2;
		} else {
			return 1;
		}
	}

}
