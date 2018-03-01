package gamecore.graph;

import java.util.ArrayList;

import gfx.Displayable;



public class Edge implements Displayable{
	
	
	//basic Graph architecture
	Node start;
	Node end;
	
	Step firststep = null; /* if length = 1, rooms must be traversed immediately. */
	Step laststep = null;
	
	//Propperties
	Boolean stepable = true;
	Boolean intelTranfer = true;
	//Boolean bidirectional = true;
	int cost = 0;
	/**
	 * Number of space between all steps. Therefore length=steps+1
	 */
	int length = 1;
	
	public Edge(Node start, Node end, int length) {
		this.start = start;
		this.end = end;
		generateSteps(length);
	}
	
	private void generateSteps(int size){
		for (; length < size; length++) {
			Step s = new Step(this, end, laststep);
			if (length == 2) {
				firststep = s;
			} else {
				laststep.setNext(s);
			}			
			laststep = s;
		}
	}
	
	Position first() {
		if (firststep==null)
			return end;
		else
			return firststep;
	}
	Position last() {
		if (laststep==null)
			return start;
		else
			return laststep;
	}
}
