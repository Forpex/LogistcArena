package gamecore;

public class GameStateIterator extends Thread{
	Game game;
	
	public GameStateIterator(Game game) {
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!game.isGameOver) {
			game.iterateGamestate();
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	
}
