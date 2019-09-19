package nl.sogyo.mancala;

public class Player {
	private Player opponent;
	private boolean myTurn;
	
	// Constructor	
	private Player(boolean myTurn) {
		this.opponent = null;
		this.myTurn = myTurn;
	}

	
	public static Player[] makePlayers() {
		
		Player player1 = new Player(true);
		Player player2 = new Player(false);
		player1.opponent = player2;
		player2.opponent = player1;
		
		return new Player[] {player1, player2};
	}
	
	
	// Getters
	public boolean getMyTurn() {
		return myTurn;
	}
	
	
	public Player getPlayer() {
		return this;
	}
	
	
	public Player getOpponent() {
		return opponent;
	}
	
	
	// Methods to switch turn with opponent
	private void nextTurn() {
		myTurn = !myTurn;
	}
	
	
	public void changeTurn() {
			nextTurn();
			opponent.nextTurn();
	}
}
