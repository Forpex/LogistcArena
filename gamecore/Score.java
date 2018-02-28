/**
 * 
 */
package gamecore;

import gfx.Displayable;

/**
 * @author Andreas Stock
 *
 */
public class Score implements Displayable{

	int[]  values;
	int size = 0;
	
	Score(int size) {

		this.size = size;
		values = new int[size];
	}
	
	void increment(int playerID) {
		if (playerID < size
			&& playerID>=0) {
			values[playerID]++;
		} else {
			System.err.println("ERROR: not a known Player!");
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String r = "";
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				r += ":";
			}
			r += values[i];
		}
		return r;
	}
	
	

}
