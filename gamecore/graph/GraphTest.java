/**
 * 
 */
package gamecore.graph;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * @author Andreas Stock
 *
 */
class GraphTest {

	

	@Test
	void test() {
		Graph g = new Graph(2,0);
		int length = 5;
		g.addEdge(g.nodes.get(0), g.nodes.get(1), length);
		int distance = 0;
		distance = g.nodes.get(0).distance(g.nodes.get(0), false);
		assertEquals(distance,0);
		System.err.println(distance);
		
		distance = g.nodes.get(1).distance(g.nodes.get(1), false);
		assertEquals(distance,0);
		System.err.println(distance);
		
		distance = g.nodes.get(0).distance(g.nodes.get(1), false);
		assertEquals(distance,length);
		System.err.println(distance);
		
		distance = g.nodes.get(1).distance(g.nodes.get(0), false);
		assertEquals(distance,length);
		System.err.println(distance);

	}

}
