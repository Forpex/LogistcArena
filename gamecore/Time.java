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
		this.seconds = seconds;
	}
	
	public void increment() {
		seconds++;
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
	
	public String toString(Game game) {
		if (game.settings.IS_DISPLAYED_TIME_INCREMENTING) {
			return (getSeconds() / 60) + ":" + (getSeconds() % 60);
		} else {
			Time derementingTime = new Time(game.settings.gamelength.seconds - this.seconds);
			return (derementingTime.getSeconds() / 60) + ":" + (derementingTime.getSeconds() % 60);
		}
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

	/**
	 * can become negative
	 * wiht items the negative number will show how long the item was available.
	 * This effect could be useful in statistics.
	 */
	public void decrement() {
		seconds--;
	}

	
	

}
