/**
 * 
 */
package gamecore.graph;

import java.util.ArrayList;

import gamecore.Item;
import gamecore.ItemMegaHealth;

/**
 * @author Andreas Stock
 *
 */
public class Graph {
	ArrayList<Node> nodes;
	ArrayList<Edge> edges;
	ArrayList<Item> items;
	
	//TODO constructor that builds a default test graph
	//TODO constructor that reads from *.lag
	
	/**
	 * Generates one Node per Item plus one empty Node somewhere
	 * Then connects them randomly, but all will be connected. No double Connections.
	 * @param V number of nodes
	 * @param p possibility of each connection.
	 */
	public Graph(int V, double p) {
		this.nodes = generateNodes(V);
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

	public Position getRandomPlayerSpawnPoint() {
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

	public ArrayList<Item> allItems() {
		return items;
	}

}
