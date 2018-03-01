package gamecore.graph;

import java.util.ArrayList;

import gfx.Displayable;



public class Edge implements Displayable{
	
	
	//basic Graph architecture
	ArrayList<Step> steps = new ArrayList<Step>(0);
	Node start;
	Node end;
	
	//Propperties
	Boolean stepable = true;
	Boolean intelTranfer = true;
	Boolean bidirectional = true;
	int cost = 0;
	int length = steps.size();
	
	public Edge(Node start, Node end, int length) {
		this.start = start;
		this.end = end;
		this.steps = generateSteps(length);
	}
	
	private ArrayList<Step> generateSteps(int size){
		ArrayList<Step> r = new ArrayList<Step>(0);
		for (int i = 0; i < size; i++) {
			r.add(new Step());
		}
		return r;
	}
}
