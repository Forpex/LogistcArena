/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

import gamecore.Settings;
import gamecore.graph.items.Item;
import gamecore.graph.items.ItemMegaHealth;

/**
 * @author Andreas Stock
 *
 */
public class Graph {
	ArrayList<Node> nodes;
	ArrayList<Edge> edges;
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
		this.nodes = generateNodes(Math.min (V, Item.TOTAL_NUMBER_OF_ITEM_TYPES));
		this.edges = generateEdgesSimple(nodes,p);		
		this.items = generateRandomItems();
	}

	private ArrayList<Item> generateRandomItems() {
		ArrayList<Item> r = new ArrayList<Item>(0);
		r.add(new ItemMegaHealth(reserveRandomItemSpawnPoint()));
		return r;
	}

	private ArrayList<Edge> generateEdgesSimple(ArrayList<Node> nodes, double p) {
		ArrayList<Edge> r = new ArrayList<Edge>(0);
		for (Node node : nodes) {
			for (Node node2 : nodes) {
				if (node != node2
						&& Math.random() <= p  //randomly leave connections out.... could be problematic TODO
						) {
					int randomLength = (int) (Math.random() * 8)+1;
					r.add(new Edge(node, node2, randomLength));
				}
			}
		}
		return r;
	}

	private ArrayList<Node> generateNodes(int V) {
		ArrayList<Node> r = new ArrayList<Node>(V);
		for (int i = 0; i < V; i++) {
			r.add(new Node());
		}
		return r;
	}

	public Position getPlayerSpawnPoint() {
		return getRandomNode();
	}

	private Node getRandomNode() {
		return nodes.get((int)Math.random()*nodes.size());
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

}
