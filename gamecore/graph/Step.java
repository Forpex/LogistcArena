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
	Position previous = next;
	

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
	public Position next(Edge n) {
		return next;
	}

	/* (non-Javadoc)
	 * @see graph.Position#moveback()
	 */
	@Override
	public Position moveback() {
		return previous;
	}

}
