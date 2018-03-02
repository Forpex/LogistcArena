package gamecore.graph;

import gamecore.Settings;
import gfx.Displayable;



public class Edge implements Displayable{
	
	
	//basic Graph architecture
	Node start;
	Node end;
	
	Step firstStepFromStart = null; /* if length = 1, edge will be traversed in a single action and no step is needed. */
	Step firstStepFromEnd = null; //=firstStepFromStart;
	
	//Propperties
	Boolean stepable = true;
	Boolean intelTranfer = true;
	Boolean isBidirectional = true;
	int cost = 0;
	
	/**
	 * Number of space between all steps. Therefore length=steps+1
	 */
	int length = 1;
	
	public Edge(Node start, Node end) {
		this(start, end, 1);
	}
	public Edge(Node start, Node end, int length) {
		this(start,end,length,true,0);
	}
	public Edge(Node start, Node end, int length, Boolean isBidirectional) {
		this(start,end,length,isBidirectional,0);
	}
	public Edge(Node start, Node end, int length, Boolean isBidirectional, int cost) {
		this.start = start;
		start.addEdge(this, true, false);
		this.end = end;
		end.addEdge(this, false, true);
		generateSteps(length, isBidirectional);
		this.isBidirectional = isBidirectional;
		
		if (Settings.isDebugOutputEnabled) {
			System.err.println(toStringWithPointers());
		}
	}
	
	private String toStringWithPointers() {
		return "Edge="+this+" start="+this.start+" end="+this.end+" l="+this.length;
	}

	/**
	 * generates a double chained list of steps of size
	 * @param size
	 * @param isBidirectional adds bidirectional Partners for each step that just face the other direction and have "no" distance from them.
	 */
	private void generateSteps(int size, Boolean isBidirectional){
		for (; length < size; length++) {
			Step s = new Step(this, end, firstStepFromEnd);
			if (length == 1) {
				firstStepFromStart = s;
				s.setPrevious(start);
			} else {
				firstStepFromEnd.setNext(s);
			}			
			firstStepFromEnd = s;
			/*if (Settings.isDebugOutputEnabled) {
				System.err.println(s.toStringWithPointers());
			}*/
		}
		if (isBidirectional) {
			if (firstStepFromStart != null) {
				
				Step iterationStep = firstStepFromStart; 					 /* start from start */
				do {
					Step newPartner = new Step(this, iterationStep.previous()
							, iterationStep.next(), iterationStep); 		/* create new bidirection partner with pointers crossed over */
					iterationStep.setBidirectionalPartner(newPartner); 
					if (iterationStep.previous() != start) { 				/* if this is not the first node, there are previous partners that need their pointers shifted to bidirectional partners */
						((Step)((Step)iterationStep.previous()).getBidirectionPartner()).setPrevious(newPartner); /* shift prious partners pointer */
						newPartner.setNext(((Step)iterationStep.previous()).getBidirectionPartner()); 			/* shift this ones pointers */
					}
					if (iterationStep.next() != end) {
						iterationStep = (Step) iterationStep.next(); 		/* go to next step but not if its the last*/
					}
				} while(iterationStep.next() != end); 						/* end if there are no more steps that need a partner */
			} else {
				; //no steps present.
			}
		}
	}
	
	Position getFirstStepEnteringFrom(Node node) {
		if (node == this.start) {
			if (firstStepFromStart==null)
				return end;
			else
				return firstStepFromStart;
		} else
		if (node == this.end
				&& isBidirectional) {
			if (firstStepFromEnd==null)
				return start;
			else
				return firstStepFromEnd;
		} else
		System.err.println("Edge:"+this+" cannot enter from here!");
		return start;
	}
}
