/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

import gamecore.graph.geometrie.Point2D;




/**
 * @author Andreas Stock
 *
 */
public class Node implements Location {
	
	Point2D relativePosition = new Point2D(0,0);

	Graph graph;
	ArrayList<Edge> edges = new ArrayList<Edge>(0);
	ArrayList<Edge> edgesIncoming = new ArrayList<Edge>(0);
	ArrayList<Edge> edgesOutgoing = new ArrayList<Edge>(0);
	Boolean hasItem = false;
	
	
	Node(Graph graph) {
		super();
		this.graph = graph;
	}
	
	Node (Graph graph, double relX, double relY){
		this(graph);
		relativePosition = new Point2D(relX, relY);
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
	 * @see graph.Location#distance(graph.Avatar, java.lang.Boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int distance(Location p, Boolean beyondNextNodes) {
		if (beyondNextNodes) {
			return distanceMessureRekursion(p, (ArrayList<Location>) graph.getNodes().clone());
		} else {
			return distanceMessureRekursion(p, new ArrayList<Location>(0));
		}
		
	}

	/* (non-Javadoc)
	 * @see graph.Location#next()
	 */
	@Override
	public Location next() {
		return this;
	}

	/* (non-Javadoc)
	 * @see graph.Location#next(graph.Edge)
	 */
	public Location next(Edge e) {
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
	public Location turn() {
		return this;
	}

	@Override
	public int distanceMessureRekursion(Location p, ArrayList<Location> alreadyvisited) {
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
	public Location next(Location towardsDestination) {
		Location r = towardsDestination;
		int distance = this.distance(towardsDestination, true);
		for (Edge edge : edgesOutgoing) {
			if (edge.contains(towardsDestination)) {
				return next(edge);
			} else {
				Location fstep = edge.getFirstStepEnteringFrom(this);
				if (distance > fstep.distance(towardsDestination, true)) {
					r = fstep;
				}
			}
		}
		return r;
	}


}
