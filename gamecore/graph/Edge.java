package gamecore.graph;

import gamecore.Settings;
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
		start.addEdge(this, true, false);
		this.end = end;
		start.addEdge(this, false, true);
		generateSteps(length);
		if (Settings.isDebugOutputEnabled) {
			System.err.println(toStringWithPointers());
		}
		
	}
	
	private String toStringWithPointers() {
		return "Edge="+this+" start="+this.start+" end="+this.end+" l="+this.length;
	}

	private void generateSteps(int size){
		for (; length < size; length++) {
			Step s = new Step(this, end, laststep);
			if (length == 1) {
				firststep = s;
				s.setPrevious(start);
			} else {
				laststep.setNext(s);
			}			
			laststep = s;
			/*if (Settings.isDebugOutputEnabled) {
				System.err.println(s.toStringWithPointers());
			}*/
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
