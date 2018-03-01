/**
 * 
 */
package gamecore;

import gfx.Displayable;

/**
 * @author Andreas Stock
 *
 */
public class Time implements Displayable{

	long seconds;
	
	public Time(long seconds) {
		this.seconds = seconds;
	}
	
	public void increment() {
		seconds += 1;
	}

	public boolean equals(Time t) {
		return (seconds == t.seconds);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (seconds / 60) + ":" + (seconds % 60);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Time clone() { //throws CloneNotSupportedException {
		return new Time(seconds);
	}
	
	

}
