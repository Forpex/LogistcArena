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
	
	Score(int size) {

		values = new int[size];
	}
	
	void increment(int playerID) {
		if (playerID < values.length
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

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Score clone() {
		Score r = new Score(values.length);
		for (int i = 0; i < values.length; i++) {
			r.values[i] = values[i];
		}
		return r;
	}
	
	
	
	

}
