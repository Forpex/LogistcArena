/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;


/**
 * @author Andreas Stock
 *
 */
public class Step implements Position {
	
	Graph graph;
	Edge mother;

	public Boolean isBidirectional() {
		return mother.isBidirectional;
	}
	
	Step(Graph graph, Edge mother) {
		super();
		this.graph = graph;
		this.mother = mother;
	}

	public String toStringWithPointers() {
		return "Step="+this+" at Edge="+this.mother+" l="+this.mother.size();
	}

	/* (non-Javadoc)
	 * @see graph.Position#distance(graph.Avatar, java.lang.Boolean)
	 */
	@Override
	public int distance(Position p) {
		
			ArrayList<Position> list = new ArrayList<Position>(0);
			return distanceMessureRekursion(p, list);
		
	}

	/* (non-Javadoc)
	 * @see graph.Position#next()
	 */
	@Override
	public Position next() {
		return mother.getnext(this);
	}
	
	private Position previous() {
		return mother.getprevious(this);
	}

	@Override
	public int distanceMessureRekursion(Position destination, ArrayList<Position> alreadyvisited) {
		if (this != destination) {
			if (alreadyvisited.contains(this)) {
				return Position.ALREADY_VISITED;
			} else {
				alreadyvisited.add(this);
				int[] options = new int[2];
				options[0] = this.previous().distanceMessureRekursion(destination, alreadyvisited) + 1;
				options[1] = this.next().distanceMessureRekursion(destination, alreadyvisited) + 1;

				int min = Position.ALREADY_VISITED;
				for (int i = 0; i < options.length; i++) {
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
		Position r = this;
		int distance = this.distance(towardsDestination);

		int dnext = this.next().distance(towardsDestination);
		if (dnext < distance) {
			r = this.next();
		} 
		int dprev = this.previous().distance(towardsDestination);
		if (//mother.isBidirectional &&
				dprev < distance) {
			r = this.previous();
		}
		return r;
	}


	

	

}
