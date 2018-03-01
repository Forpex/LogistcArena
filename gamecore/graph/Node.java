/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

import gfx.Displayable;


/**
 * @author Andreas Stock
 *
 */
public class Node implements Position ,Displayable {

	ArrayList<Edge> edges;
	Boolean hasItem;
	
	
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
		return this;
	}

	/* (non-Javadoc)
	 * @see graph.Position#next(graph.Edge)
	 */
	public Position next(Edge e) {
		if (edges.contains(e)
				&& edges.size() != 0) {
			if (this == e.start
					|| this == e.end) {
				return e.first();
			} /*else {
				if (this == e.end
						&& e.bidirectional) {
					return e.laststep;
				}*/ else {
					System.err.println("ERROR: this edge does not start here: " + e);
					return this;
				}
			//}
		} else {
			System.err.println("ERROR: this edge is not connected: " + e);
			return this;
		}
	}

	@Override
	public Position next(int chosenPathID) {
		if (chosenPathID < 0) {
			return next();
		}
		return next(edges.get(chosenPathID % edges.size()));	
		
	}

	@Override
	public int getNumPathChoices() {
		
		return edges.size();
	}


}
