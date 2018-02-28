package gamecore.graph;

import java.util.ArrayList;

import gfx.Displayable;



public class Edge implements Displayable{
	
	//basic Graph architecture
	ArrayList<Step> steps = new ArrayList<>(0);
	Node start;
	Node end;
	
	//Propperties
	Boolean stepable = true;
	Boolean intelTranfer = true;
	Boolean bidirectional = true;
	int cost = 0;
	int length = 1;
	
	
}
