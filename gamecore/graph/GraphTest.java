/**
 * 
 */
package gamecore.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Andreas Stock
 *
 */
class GraphTest {

	

	@Test
	void simpleTwoNodesAndLink() {
		Graph g = new Graph(2,0);
		int length = 5;
		g.addEdge(g.getNodes().get(0), g.getNodes().get(1), length);
		int distance = 0;
		distance = g.getNodes().get(0).distance(g.getNodes().get(0));
		assertEquals(0,distance);
		System.err.println(distance);
		
		distance = g.getNodes().get(1).distance(g.getNodes().get(1));
		assertEquals(0,distance);
		System.err.println(distance);
		
		distance = g.getNodes().get(0).distance(g.getNodes().get(1));
		assertEquals(length,distance);
		System.err.println(distance);
		
		distance = g.getNodes().get(1).distance(g.getNodes().get(0));
		assertEquals(length,distance);
		System.err.println(distance);

	}

}
