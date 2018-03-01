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
	 */
	Graph() {
		for (int i = 0; i < Item.length+1; i++) {
			this.nodes.add(new Node());
		}
		for (Node node : nodes) {
			for (Node node2 : nodes) {
				if (node != node2
						&& Math.random() <= 0.8  //randomly leave connections out.... could be problematic TODO
						) {
					int length = (int) (Math.random() * 8)+1;
					this.edges.add(new Edge(node, node2, length));
				}
			}
		}
		
		this.items.add(new ItemMegaHealth(reserveRandomItemSpawnPoint()));
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
