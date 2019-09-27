package nl.sogyo.mancala.domain;

public class Player {
	private Player opponent;
	private boolean myTurn;

	// Constructor
	protected Player() {
		this.myTurn = true;
		this.opponent = new Player(this);
	}

	private Player(Player opponent) {
		this.opponent = opponent;
		this.myTurn = !opponent.myTurn;
	}

	// Getters
	public boolean hasTurn() {
		return myTurn;
	}

	public Player getOpponent() {
		return opponent;
	}

	private void nextTurn() {
		myTurn = !myTurn;
	}

	public void changeTurn() {
		nextTurn();
		opponent.nextTurn();
	}
}
