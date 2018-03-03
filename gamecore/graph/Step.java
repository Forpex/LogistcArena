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
public class Step implements Position, Displayable {
	
	Graph graph;
	Edge mother;
	
	/**
	 * its the step with distance zero thats next is in the other direction.
	 * This is a crude but working method to let avatars move in two directions.
	 * @return the bidirectionalPartner
	 */
	public Position getBidirectionalPartner() {
		return mother.getBidirectionalPartner(this);
	}


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
	@SuppressWarnings("unchecked")
	@Override
	public int distance(Position p, Boolean justIntel) {
		if (justIntel) {
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
		return mother.getnext(this);
	}
	
	private Position previous() {
		return mother.getprevious(this);
	}

	/* (non-Javadoc)
	 * @see graph.Position#next(graph.Edge)
	 */
	@Override
	public Position next(int chosenPathID) {
		return next();
	}

	/* (non-Javadoc)
	 * @see gamecore.graph.Position#getNumPathChoices()
	 */
	@Override
	public int getNumOutgoingEdges() {
		return 0;
	}

	@Override
	public Position turn() {
		if (isBidirectional() != null) {
			return getBidirectionalPartner();
		} else {
			System.err.println("Step:"+ this +" cannot Turn around here!");
			return this;
		}
	}

	@Override
	public int distanceMessureRekursion(Position p, ArrayList<Position> alreadyvisited) {
		if (this != p) {
			if (alreadyvisited.contains(this)) {
				return Integer.MIN_VALUE;
			} else {
				alreadyvisited.add(this);
				int[] options = new int[3];
				options[0] = this.previous().distanceMessureRekursion(p, alreadyvisited) + 1;
				options[1] = this.next().distanceMessureRekursion(p, alreadyvisited) + 1;
				options[2] = this.turn().distanceMessureRekursion(p, alreadyvisited) + 0;

				int min = Integer.MAX_VALUE;
				for (int i = 0; i < options.length; i++) {
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

	

}
