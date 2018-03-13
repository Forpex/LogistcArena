/**
 * 
 */
package controls.console;

import java.util.ArrayList;
import java.util.Scanner;

import controls.Player;
import gamecore.Intel;
import gamecore.Settings;
import gamecore.avatars.Avatar;
import gamecore.graph.Graph;
import gamecore.graph.Node;
import gamecore.graph.Position;
import gamecore.graph.geometrie.Point2D;
import gamecore.graph.items.Item;

/**
 * This is just simply to Test. Inputs will be done with console along with debugging outputs.
 * display method will be a grid of char[][]. and user imput will be keys.
 * 
 * @author Andreas Stock
 *
 */
public class ConsolePlayer extends Player {

	private static final int gridsizeY = 16;
	private static final int gridsizeX = gridsizeY;
	private Scanner keyboard;


	public ConsolePlayer(int id) {
		super(id);
	}

	@Override
	public void post(Intel intel) {
		this.intel = intel;
		if (Settings.isDebugOutputEnabled) {
			System.out.println("IntelGotten: " + intel.toString());
		}
		draw();
		//askForInput();
		//super.setDestination(intel.graph.getASpawnPoint(Graph.extractAvatarPositions(intel.visibleEnemyAvatars), 3));
	}

	/* (non-Javadoc)
	 * @see controls.Player#run()
	 */
	@Override
	public void run() {
		super.run();
		do {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (intel == null);
		boolean exit = false;
		while(!(exit || intel.isGameOver)) {
			//draw graph in ascii art
			//draw(); no, because.
			
			//scanner and input
		 int choice = askForInput();
		 
		 //end cycle
		 if (choice < 0) {
			 choice = 0;
			exit = true;
		}
			
			//pick node that corresponds to input id

	    ArrayList<Node> nodes = intel.graph.getNodes();
	    choice = choice % nodes.size();
		setDestination(nodes.get(choice));
		
			// repeat
		}
		
	}

	public int askForInput() {
		keyboard = new Scanner(System.in);
    	//System.out.println("Choose a node as destination:");
        return  keyboard.nextInt();
       
	}
	
	

	
	public void draw() {
		String[][] grid = generateGrid(intel.graph);
		applyToGrid(intel.graph, grid);
		applyToGrid(intel.self, grid, 
				(!intel.self.isAlive())?"xyx":"You");
		for (Avatar a : intel.visibleEnemyAvatars) {
			applyToGrid(a,grid, 
					(!a.isAlive())?"xxx":"Bot");
		}
		
		String s = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
			s += "------------------------\n";
			s += "Welcome to LogisticArena\n";
			s += "------------------------\n";
		
		
		
		for (int i = 0; i < grid.length; i++) {
			String line = " ";
			for (int j = 0; j < grid[i].length; j++) {
				line += grid[i][j];
			}
			while (line.length() < gridsizeX*3) {
				line += " ";
			}
			line += "| ";
			switch (i) {
			case 0:
				line +="Time: "+intel.currentTime;
				break;
			case 1:
				line += "Health: "+ intel.self.getHealth();
				break;	
			case 2:
				line += "Armor: "+ intel.self.getArmor();
				break;
				
			//WEAPONS
			case 4:
				line += intel.self.getWeaponByName("shotgun").toString();
				break;
			case 5:
				line += intel.self.getWeaponByName("lightning").toString();
				break;
			case 6:
				line += intel.self.getWeaponByName("railgun").toString();
				break;
			case 7:
				line += intel.self.getWeaponByName("pistol").toString();
				break;
			
				//SCORE	
			case 9:
				line += "Score: "+intel.currentScore;
				break;
				
			default:
				break;
			}
			

			
			s += line +"\n";
		}
		if (Settings.isDebugOutputEnabled) {
			s+= "p       = "+intel.self.getPosition()+"\n";
			s+= "next    = "+intel.self.getPosition().next(getDestination())+"\n";
			s+= "d       = "+getDestination()+"\n";
			s+= "distance= "+intel.self.getPosition().distance(getDestination())+"\n";
		}
		s += "Choose a nodeID as destination:\n";
		System.out.print(s);
	}

	private void applyToGrid(Avatar a, String[][] grid, String symbol) {
		Point2D relpos = getAvatarRelativePosition(a);
		int y = relPosToIntY(relpos);
		int x = relPosToIntX(relpos);
		grid[y][x] = symbol + grid[y][x];
		
	}

	public Point2D getAvatarRelativePosition(Avatar a) {
		Position p = a.getPosition();
		
		Node n0 = intel.graph.getNodes().get(0);
		Node n1 = intel.graph.getNodes().get(1);
		int distance0 = Integer.MAX_VALUE;
		int distance1 = Integer.MAX_VALUE;
		
		for (Node node : intel.graph.getNodes()) {
			int distance = p.distance(node);
			if (distance <distance0 ) {
				n1 = n0;
				distance1 = distance0;
				n0 = node;
				distance0 = distance;
			} else {
				if (distance < distance1) {
					n1 = node;
					distance1 = distance;
				}
			}
		}
		return new Point2D(n0.relativePosition, (double)distance0, n1.relativePosition, (double)distance1);
	}

	public String[][] generateGrid(Graph g) {
		String[][] grid = new String[gridsizeY][gridsizeX];
		for (int i = 0; i < gridsizeY; i++) {
			for (int j = 0; j < gridsizeX; j++) {
				grid[i][j] = "  ";
			}
		}
		return grid;
	}

	public void applyToGrid(Graph g, String[][] grid) {
		for (Node n : g.getNodes()) {
			int y = relPosToIntY(n.relativePosition);
			int x = relPosToIntX(n.relativePosition);
			String symbol = String.valueOf(g.getNodes().indexOf(n));
			for (Item item : g.getItems()) {
				if (item.getPosition() == n
						&& item.isPickupable()) {
					symbol += String.valueOf(abbreviation(item)); 
				}
			}
			while (symbol.length() < 2) {
				symbol += " ";
			}
			grid[y][x] = symbol;
		}
	}

	public int relPosToIntY(Point2D relpos) {
		return (int) (relpos.y * (double)(gridsizeY-1));
	}
	public int relPosToIntX(Point2D relpos) {
		return (int) (relpos.x * (double)(gridsizeX-1));
	}

	private String abbreviation(Object o) {
		String r = " ";
		String c = o.getClass().getSimpleName();
		switch (c) {
		case "ItemMegaHealth":
			r = "Mh";
			break;
		case "ItemRedArmor":
			r = "Ra";
			break;	
		case "ItemYellowArmor":
			r = "Ya";
			break;
			
		case "AmmoShotgun":
			r = "Sg";
			break;	
		case "AmmoLightning":
			r = "Lg";
			break;	
		case "AmmoRailgun":
			r = "Rg";
			break;
			
			
		default:
			break;
		}
		
		return r;
	}
	
	
}
