/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

import gamecore.Settings;

/**
 * @author Andreas Stock
 *
 */
public class Step implements Location {
	
	Graph graph;
	Edge mother;
	
	/**
	 * its the step with distance zero thats next is in the other direction.
	 * This is a crude but working method to let avatars move in two directions.
	 * @return the bidirectionalPartner
	 */
	public Location getBidirectionalPartner() {
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
		return mother.getnext(this);
	}
	
	private Location previous() {
		return mother.getprevious(this);
	}



	@Override
	public Location turn() {
		if (isBidirectional()) {
			return getBidirectionalPartner();
		} else {
			if (Settings.isDebugOutputEnabled) {
				System.err.println("Step:" + this + " cannot Turn around here!");
			}
			return this;
		}
	}

	@Override
	public int distanceMessureRekursion(Location p, ArrayList<Location> alreadyvisited) {
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


	@Override
	public Location next(Location towardsDestination) {
		Location r = towardsDestination;
		int distance = this.distance(towardsDestination, true);
		
		if (this.next().distance(towardsDestination, true) < distance) {
			r = this.next();
		} 
		if (this.turn().next().distance(towardsDestination, true) < distance) {
			r = this.turn().next();
		}
		return r;
	}

	

}
