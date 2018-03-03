/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;
import java.util.Iterator;

import gamecore.Settings;
import gamecore.graph.items.Item;
import gamecore.graph.items.ItemMegaHealth;
import gamecore.graph.items.ItemRedArmor;

/**
 * @author Andreas Stock
 *
 */
public class Graph {
	private ArrayList<Node> nodes;
	ArrayList<Edge> edges = new ArrayList<Edge>(0);
	private ArrayList<Item> items;
	
	//TODO constructor that builds a default test graph
	//TODO constructor that reads from *.lag
	
	/**
	 * Generates one Node per Item plus one empty Node somewhere
	 * Then connects them randomly, but all will be connected. No double Connections.
	 * @param V number of nodes (cannot be smaller than number of Items!)
	 * @param p possibility of each connection.
	 */
	public Graph(int V, double p) {
		this.setNodes(generateNodes(Math.max (V, Settings.TOTAL_NUMBER_OF_ITEM_TYPES)));
		this.edges = generateEdgesSimple(getNodes(),p);
		this.items = generateRandomItems();
	}
	
	public void addEdge(Node node1, Node node2, int length) {
		edges.add(new Edge(this, node1, node2, length));
	}

	private ArrayList<Item> generateRandomItems() {
		ArrayList<Item> r = new ArrayList<Item>(0);
		r.add(new ItemMegaHealth(reserveRandomItemSpawnPoint()));
		r.add(new ItemRedArmor(reserveRandomItemSpawnPoint()));
		return r;
	}

	private ArrayList<Edge> generateEdgesSimple(ArrayList<Node> nodes, double p) {
		ArrayList<Edge> r = new ArrayList<Edge>(0);
		
		for (int i = 0; i < nodes.size(); i++) {
			Node node1 = nodes.get(i);
			for (int j = i+1; j < nodes.size(); j++) {
				Node node2 = nodes.get(j);
				if (node1 != node2
						&& Math.random() <= p  //randomly leave connections out.... could be problematic TODO
						) {
					int simpleLength = Settings.EDGE_SIMPLE_STANDART_LENGTH;
					r.add(new Edge(this, node1, node2, simpleLength));
				}
			}
		}
		return r;
	}

	private ArrayList<Node> generateNodes(int V) {
		ArrayList<Node> r = new ArrayList<Node>(V);
		for (int i = 0; i < V; i++) {
			r.add(new Node(this));
		}
		return r;
	}

	public Position getPlayerSpawnPoint(ArrayList<Position> enemies) {
		ArrayList<Position> positions = new ArrayList<Position>(0);
		for (Position p : this.getNodes()){
			if (enemies.size()==0) {
				positions.add(p);
			} else 
				for (Position e : enemies)  {
					if (p.distance(e, true) >= Settings.MINIMAL_SPAWN_DISTANCE) {
						positions.add(p);
					}
			}
		}
		if (Settings.isDebugOutputEnabled) {
			System.out.println("ValidSpawnPositionSize=" + positions.size());
		}
		if(positions.size() == 0)
			return getRandomNode();
		else
			return positions.get((int)(Math.random()*positions.size()));
	}

	private Node getRandomNode() {
		return getNodes().get((int)(Math.random()*getNodes().size()));
	}

	/**
	 * Makes problems, if (items < nodes) //TODO
	 * @return
	 */
	public Position reserveRandomItemSpawnPoint() {
		Node r;
		do {
			r = getRandomNode();
		} while (r.hasItem);
		r.hasItem = true;
		return r;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public ArrayList<Item> getItemsInSight(Position p) {
		ArrayList<Item> r = new ArrayList<Item>(0);
		for (Item i : items) {
			if(i.getPosition().distance(p, true) <= Settings.INTEL_DISTANCE) {
				r.add(i);
			}
		}
		return r;
	}
	
	public ArrayList<Position> getAllPositions(){
		ArrayList<Position> r = new ArrayList<Position>(0);
		for (Node node : getNodes()) {
			r.add(node);
		}
		for (Edge edge : edges) {
			for (Position p : edge.stepsforward) {
				r.add(p);
			}
			/*for (Position p : edge.stepsbackward) {
				r.add(p);
			}*/
		}
		return r;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

}
