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

	private long seconds;
	
	public Time(long seconds) {
		this.setSeconds(seconds);
	}
	
	public void decrement() {
		setSeconds(getSeconds() - 1);
	}

	public boolean equals(Time t) {
		return (getSeconds() == t.getSeconds());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (getSeconds() / 60) + ":" + (getSeconds() % 60);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Time clone() { //throws CloneNotSupportedException {
		return new Time(getSeconds());
	}

	public long getSeconds() {
		return seconds;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}
	
	

}
