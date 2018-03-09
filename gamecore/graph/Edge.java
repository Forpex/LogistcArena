package gamecore.graph;

import java.util.ArrayList;

import gamecore.Settings;



public class Edge{
	
	
	//basic Graph architecture
	Graph graph;
	Node start;
	Node end;
	
	ArrayList<Step> stepsforward = new ArrayList<Step>(0);
	
	//Propperties
	Boolean stepable = true;
	Boolean intelTranfer = true;
	Boolean isBidirectional = true;
	int cost = 0;
	
	/**
	 * Number of space between all steps. Therefore length=steps+1
	 */
	public int size() {
		return stepsforward.size()+1;
	}
	
	public Edge(Graph graph,Node start, Node end) {
		this(graph,start, end, 1);
	}
	public Edge(Graph graph,Node start, Node end, int length) {
		this(graph, start, end, length, true, 0);
	}
	public Edge(Graph graph,Node start, Node end, int length, Boolean isBidirectional) {
		this(graph, start, end, length, isBidirectional, 0);
	}
	public Edge(Graph graph,Node start, Node end, int length, Boolean isBidirectional, int cost) {
		this.graph = graph;
		graph.edges.add(this);
		this.start = start;
		start.addEdge(this, true, isBidirectional);
		this.end = end;
		end.addEdge(this, isBidirectional, true);
		generateSteps(Math.max(1, length));
		this.isBidirectional = isBidirectional;
		
		if (Settings.isDebugOutputEnabled) {
			System.err.println(toStringWithPointers());
		}
	}
	
	public Edge(Graph graph, int node1id, int node2id, int length) {
		this(graph, graph.getNodes().get(node1id), 
				graph.getNodes().get(node2id) , length);
	}

	private void generateSteps(int length) {
		for (int i = 1; i < length; i++) {
			stepsforward.add(new Step(graph, this));
		}		
	}
	private String toStringWithPointers() {
		return "Edge="+this+" start="+this.start+" end="+this.end+" l="+this.size();
	}
	
	Position getFirstStepEnteringFrom(Node node) {
		if (node == this.start) {
			if (size() == 1)
				return end;
			else
				return stepsforward.get(0);
		} else
		if (//isBidirectional&&
				node == this.end) {
			if (size() == 1)
				return start;
			else
				return stepsforward.get(stepsforward.size()-1);
		} else
		System.err.println("Edge:"+this+" cannot enter from here!");
		return start;
	}
	
	public Position getnext(Step s) {
		int i = stepsforward.indexOf(s);
		if(i != -1) {
			if (i >= stepsforward.size()-1) {
				return end;
			} else {
				return stepsforward.get(i+1);
			}
		} 
		return null;
	}
	
	public Position getprevious(Step s) {
		int i = stepsforward.indexOf(s);
		if(i != -1) {
			if (i == 0) {
				return start;
			} else {
				return stepsforward.get(i-1);
			}
		}
		return null;
	}

	public boolean contains(Position p) {
		if (stepsforward.contains(p)) {
			return true;			
		}
		return false;
	}

	public Node getExitEnteringFrom(Node node) {
			if (node == this.start) {
					return end;
			} else
			if (node == this.end
					&& isBidirectional) {
					return start;
			} else
			System.err.println("Edge:"+this+" cannot enter from Node:"+node+" !!!");
			return start;
	}
}
