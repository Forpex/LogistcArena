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

	ArrayList<Edge> edges = new ArrayList<Edge>(0);
	ArrayList<Edge> edgesIncoming = new ArrayList<Edge>(0);
	ArrayList<Edge> edgesOutgoing = new ArrayList<Edge>(0);
	Boolean hasItem = false;
	
	
	void addEdge(Edge e, Boolean outgoing, Boolean incoming){
		edges.add(e);
		if (incoming) {
			edgesIncoming.add(e);
		}
		if (outgoing) {
			edgesOutgoing.add(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see graph.Position#distance(graph.Avatar, java.lang.Boolean)
	 */
	@Override
	public int distance(Position p, Boolean justIntel) {
		ArrayList<Position> alreadyvisited = new ArrayList<Position>(0);
		alreadyvisited.add(this);
		return distanceMessureRekursion(p, alreadyvisited);
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
		if (edgesOutgoing.contains(e)
				&& edgesOutgoing.size() != 0) {
			if (this == e.start
					|| this == e.end) {
				return e.getFirstStepEnteringFrom(this);
			} /*else {
				if (this == e.end
						&& e.isBidirectional) {
					return e.firstStepFromEnd;
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
	public int getNumOutgoingEdges() {
		
		return edgesOutgoing.size();
	}

	@Override
	public Position turn() {
		return this;
	}

	@Override
	public int distanceMessureRekursion(Position p, ArrayList<Position> alreadyvisited) {
		if (this != p) {
			if (alreadyvisited.contains(p)) {
				return Integer.MAX_VALUE;
			} else {
				alreadyvisited.add(this);
				int r = Math.min(
							distanceMessureRekursion(this.next(), alreadyvisited)+1
							,
							distanceMessureRekursion(this.turn(), alreadyvisited)+0
							);
				for (Edge edge : edges) {
					r = Math.min(r, distanceMessureRekursion(this.next(edge), alreadyvisited));
				}
				return r;
			}
		} else {
			return 0;
		}
	}


}
