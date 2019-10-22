package nl.sogyo.mancala.domain;

public class Kalaha extends AbstractBowl {
    // jeroen is lief
	// --- Constructor ---
	protected Kalaha(int beadsInBowls, int amountOfBowls, Player owner, Bowl firstBowl) {
		super();
		this.beads = 0;
		this.owner = owner;

		// Starts the other line of bowls once made. No more -1 since there is no
		// pre-existing bowl.
		if (owner == firstBowl.getOwner()) {
			this.neighbour = new Bowl(beadsInBowls, amountOfBowls, amountOfBowls, owner.getOpponent(), firstBowl);
		} else {
			this.neighbour = firstBowl;
		}
	}

	// --- Kalaha Methods ---
	protected  int takeBead(int beadsInHand) {
		if (owner.getMyTurn() == true) {
			beads += 1;
			beadsInHand -= 1;
		}
		return beadsInHand;
	}

	protected void distribute(int beadsInHand, Player thisTurnsPlayer) {
		if (thisTurnsPlayer.equals(owner)) {
			beadsInHand = takeBead(beadsInHand);
		}
		if (beadsInHand > 0) {
			neighbour.distribute(beadsInHand, thisTurnsPlayer);
		}
	}

	protected void putBeadsInKalaha(int givenBeads, Player thisTurnsPlayer) {
		if (thisTurnsPlayer.equals(owner)) {
			beads += givenBeads;
		} else {
			neighbour.putBeadsInKalaha(givenBeads, thisTurnsPlayer);
		}
	}

	// Because bowl returns neighbour of the return it gets, kalaha will return
	// itself to give its neighbour.
	protected  AbstractBowl findOpposite() {
		return this;
	}

	// If the check reaches kalaha, every bowl is empty, thus returns true.
	protected boolean checkBowlsEmpty() {
		return true;
	}
}
