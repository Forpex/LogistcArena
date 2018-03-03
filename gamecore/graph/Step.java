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
	
	Edge mother;
	private Position next;
	private Position previous;
	/**
	 * its the step with distance zero thats next is in the other direction.
	 * This is a crude but working method to let avatars move in two directions.
	 */
	private Position bidirectionalPartner = null;
	
	/**
	 * @return the bidirectionalPartner
	 */
	public Position getBidirectionPartner() {
		return bidirectionalPartner;
	}

	/**
	 * @param bidirectionalPartner the bidirectionalPartner to set
	 */
	public void setBidirectionalPartner(Position newBidirectionalPartner) {
		this.bidirectionalPartner = newBidirectionalPartner;
	}

	public Boolean isBidirectional() {
		return bidirectionalPartner != null;
	}
	
	Step(Edge mother, Position next, Position previous) {
		super();
		this.mother = mother;
		this.next = next;
		this.previous = previous;
	}
	
	Step(Edge mother, Position next, Position previous, Position bidirectionalPartner) {
		super();
		this.mother = mother;
		this.next = next;
		this.previous = previous;
		this.bidirectionalPartner = bidirectionalPartner;
	}

	public String toStringWithPointers() {
		return "Step="+this+" at Edge="+this.mother+" l="+this.mother.length+" n="+this.next+" p="+this.previous;
	}
	

	/**
	 * @return the previous
	 */
	Position previous() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	void setPrevious(Position previous) {
		this.previous = previous;
	}

	/**
	 * @param next the next to set
	 */
	void setNext(Position next) {
		this.next = next;
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
		return next;
		
	}

	/* (non-Javadoc)
	 * @see graph.Position#next(graph.Edge)
	 */
	@Override
	public Position next(int chosenPathID) {
		return next;
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
		if (bidirectionalPartner != null) {
			return bidirectionalPartner;
		} else {
			System.err.println("Step:"+ this +" cannot Turn around here!");
			return this;
		}
	}

	@Override
	public int distanceMessureRekursion(Position p, ArrayList<Position> alreadyvisited) {
		if (this != p) {
			if (alreadyvisited.contains(p)) {
				return Integer.MAX_VALUE;
			} else {
				alreadyvisited.add(this);
				return Math.min(
							Math.min(
									distanceMessureRekursion(this.previous(), alreadyvisited)+1
									,
									distanceMessureRekursion(this.next(), alreadyvisited)+1
									)
							,
							distanceMessureRekursion(this.turn(), alreadyvisited)+0
							);
			}
		} else {
			return 0;
		}
	}

}
