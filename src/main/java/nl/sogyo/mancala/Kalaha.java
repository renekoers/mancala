package nl.sogyo.mancala;

public class Kalaha extends AbstractBowl {

	public Kalaha() {
		super();
		this.beads = 0;
	}
	
	
	public int takeBead(int beadsInHand) {
		if (owner.getMyTurn() == true) {
			beads += 1;
			beadsInHand -= 1;
		}
		return beadsInHand;
	}
}
