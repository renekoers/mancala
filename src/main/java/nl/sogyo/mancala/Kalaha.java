package nl.sogyo.mancala;

public class Kalaha extends AbstractBowl {

	
	// --- Constructor ---
	public Kalaha(int beadsInBowls, int amountOfBowls, Player owner, Bowl firstBowl) {
		super();
		this.beads = 0;
		this.owner = owner;
		
		// Starts the other line of bowls once made. No more -1 since there is no pre-existing bowl.
		if (owner == firstBowl.getOwner()) {
			this.neighbour = new Bowl(beadsInBowls, amountOfBowls, amountOfBowls, owner.getOpponent(), firstBowl);
		} else {
			this.neighbour = firstBowl;
		}
	}
	
	
	// --- Kalaha Methods ---
	public int takeBead(int beadsInHand) {
		if (owner.getMyTurn() == true) {
			beads += 1;
			beadsInHand -= 1;
		}
		return beadsInHand;
	}
	
	
	// Only takes a bead if it's the owner's turn, doesn't change turn if kalaha takes the last bead.
	public void distribute(int beadsInHand, Player thisTurnsPlayer) {
		if (thisTurnsPlayer.equals(owner)) {
			beadsInHand = takeBead(beadsInHand);
		}
		if (beadsInHand > 0) {
			neighbour.distribute(beadsInHand, thisTurnsPlayer);
		} else {
			owner.changeTurn();
		}
	}
	
	
	// Only takes the beads if kalaha belongs to the player of this turn.
	protected void putBeadsInKalaha(int givenBeads, Player thisTurnsPlayer) {
		if (thisTurnsPlayer.equals(owner)) {
			beads += givenBeads;
		} else {
			neighbour.putBeadsInKalaha(givenBeads, thisTurnsPlayer);
		}
	}
	
	
	// Returns itself since bowl's method returns the kalaha's neighbour.
	public AbstractBowl findOpposite() {
		return this;
	}
	
	
	// If the check reaches kalaha, every bowl is empty. thus returns true.
	protected boolean checkBowlsEmpty(){
		return true;
	}
}
