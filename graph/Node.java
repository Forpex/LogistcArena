/**
 * 
 */
package graph;

import java.util.ArrayList;


/**
 * @author Andreas Stock
 *
 */
public class Node implements Position {

	ArrayList<Edge> edges;
	
	
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
		// TODO Auto-generated method stub
		return this;
	}

	/* (non-Javadoc)
	 * @see graph.Position#next(graph.Edge)
	 */
	@Override
	public Position next(Edge e) {
		if (edges.contains(e)
				&& edges.size() != 0) {
			if (this == e.start) {
				return e.steps.get(0);
			} else {
				if (this == e.end
						&& e.bidirectional) {
					return e.steps.get(e.steps.size()-1);
				} else {
					System.err.println("ERROR: this edge does not start nor end here: " + e);
					return this;
				}
			}
		} else {
			System.err.println("ERROR: this edge is not connected: " + e);
			return this;
		}
	}

	/* (non-Javadoc)
	 * @see graph.Position#moveback()
	 */
	@Override
	public Position moveback() {
		// TODO Auto-generated method stub
		return this;
	}


}
