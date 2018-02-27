package graph;

import java.util.ArrayList;



public class Edge{
	
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
