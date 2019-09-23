package nl.sogyo.mancala;

public class Player {
	private Player opponent;
	private boolean myTurn;
	
	// Constructor	
	public Player() {
		this.myTurn = true;
		this.opponent = new Player(this);
	}

	
	private Player(Player opponent) {
		this.opponent = opponent;
		this.myTurn = !opponent.myTurn;
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
