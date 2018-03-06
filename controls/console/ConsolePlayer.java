/**
 * 
 */
package controls.console;

import controls.Player;
import gamecore.Intel;
import gamecore.avatars.Avatar;
import gamecore.graph.Graph;
import gamecore.graph.Node;
import gamecore.graph.items.Item;

/**
 * This is just simply to Test. Inputs will be done with console along with debugging outputs.
 * display method will be a grid of char[][]. and user imput will be keys.
 * 
 * @author Andreas Stock
 *
 */
public class ConsolePlayer extends Player {

	private static final int consolesizeY = 10;
	private static final int consolesizeX = consolesizeY;


	public ConsolePlayer(int id) {
		super(id);
	}

	@Override
	public void post(Intel intel) {
		this.intel = intel;
		System.out.println("IntelGotten: "+intel.toString());
		System.out.println("Time: "+intel.currentTime);
		draw();
	}

	/* (non-Javadoc)
	 * @see controls.Player#run()
	 */
	@Override
	public void run() {
		super.run();
		
		do {
			//draw graph in ascii art
			// ^^ need relative node positions for this first.
			
			//scanner and input
			
			//pick node that corresponds to input id
			
			super.setDestination(intel.graph.getASpawnPoint(Graph.extractAvatarPositions(intel.visibleEnemyAvatars), 3));
		} while (!intel.isGameOver || super.getDestination() == null);
	}

	
	public void draw() {
		char[][] grid = generateGrid(intel.graph);
		applyToGrid(intel.graph, grid);
		applyToGrid(intel.self, grid);
		
		String s = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				s += " "+grid[i][j];
			}
			s += " | ";
			switch (i) {
			case 0:
				s += "-----------";
				break;
			case 1:
				s += "Health: "+ intel.self.getHealth();
				break;	
			case 2:
				s += "Armor: "+ intel.self.getArmor();
				break;
				
			//WEAPONS
			case 4:
				s += intel.self.getWeaponByName("shotgun").toString();
				break;
			case 5:
				s += intel.self.getWeaponByName("lightning").toString();
				break;
			case 6:
				s += intel.self.getWeaponByName("railgun").toString();
				break;
			case 7:
				s += intel.self.getWeaponByName("pistol").toString();
				break;
				
			default:
				break;
			}
			

			System.out.println(s);
			s = "";
		}
	}

	private void applyToGrid(Avatar self, char[][] grid) {
		// TODO Auto-generated method stub
		
	}

	public char[][] generateGrid(Graph g) {
		char[][] grid = new char[consolesizeY][consolesizeX+1];
		for (int i = 0; i < consolesizeY; i++) {
			for (int j = 0; j < consolesizeX; j++) {
				grid[i][j] = ' ';
			}
		}
		return grid;
	}

	public void applyToGrid(Graph g, char[][] grid) {
		for (Node n : g.getNodes()) {
			int y = (int) (n.relativePosition.y * (consolesizeY-1));
			int x = (int) (n.relativePosition.x * (consolesizeX-1));
			char symbol = String.valueOf(g.getNodes().indexOf(n)).charAt(0);
			grid[y][x] = symbol;
			for (Item item : g.getItems()) {
				if (item.getPosition() == n
						&& item.isPickupable()) {
					grid[y][x+1] = representableCharacter(item); 
				}
			}
		}
	}

	private char representableCharacter(Object o) {
		char r = ' ';
		String c = o.getClass().getSimpleName();
		switch (c) {
		case "ItemMegaHealth":
			r = 'M';
			break;

		default:
			break;
		}
		
		return r;
	}
	
	
}
