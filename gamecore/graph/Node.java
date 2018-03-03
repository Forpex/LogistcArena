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

	Graph graph;
	ArrayList<Edge> edges = new ArrayList<Edge>(0);
	ArrayList<Edge> edgesIncoming = new ArrayList<Edge>(0);
	ArrayList<Edge> edgesOutgoing = new ArrayList<Edge>(0);
	Boolean hasItem = false;
	
	
	Node(Graph graph) {
		super();
		this.graph = graph;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public int distance(Position p, Boolean beyondNextNodes) {
		if (beyondNextNodes) {
			return distanceMessureRekursion(p, (ArrayList<Position>) graph.nodes.clone());
		} else {
			return distanceMessureRekursion(p, new ArrayList<Position>(0));
		}
		
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
		if (chosenPathID < 0
				|| edges.size()<=0) {
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
			if  (alreadyvisited.contains(this)){
				return Integer.MIN_VALUE;
			} else {
				alreadyvisited.add(this);
				
				int min = Integer.MAX_VALUE;
				int[] options = new int[edges.size()];
				for (int i = 0; i < edges.size(); i++) {
					Edge e = edges.get(i);
					if (e.contains(p)) {
						options[i] = e.getFirstStepEnteringFrom(this).distanceMessureRekursion(p,
								alreadyvisited) + 1;
					}else {
						options[i] = e.getExitEnteringFrom(this).distanceMessureRekursion(p,
								alreadyvisited) + e.size();
					}
					//-----
					if (options[i] < 0) {
						options[i] = Integer.MAX_VALUE;
					}
					min = Math.min(min, options[i]);
				}
				return min;
			}
		} else {
			return 0;
		}
	}

	@Override
	public Position next(Position towardsDestination) {
		Position r = towardsDestination;
		int distance = this.distance(towardsDestination, true);
		for (Edge edge : edgesOutgoing) {
			if (edge.contains(towardsDestination)) {
				return next(edge);
			} else {
				Position fstep = edge.getFirstStepEnteringFrom(this);
				if (distance > fstep.distance(towardsDestination, true)) {
					r = fstep;
				}
			}
		}
		return r;
	}


}
