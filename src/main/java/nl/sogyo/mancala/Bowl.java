package nl.sogyo.mancala;

public class Bowl extends AbstractBowl {
	
	
	// Constructor
	public Bowl() {
		super();
		this.beads = 4;
	}
	
	
	public void orderPlayerChange() {
		owner.changeTurn();
	}
	
	
	public int takeBead(int beadsInHand) {
		beads += 1;
		beadsInHand -= 1;
		return beadsInHand;
	}
	
	
	public void startDistribute() {
		int grabbedBeads = beads;
		beads = 0;
		neighbour.distribute(grabbedBeads, owner);
	}
	
	
	public void emptyBowl() {
		beads = 0;
	}
	
	
	
	public boolean isAllEmpty(Player thisTurnsPlayer) {
		boolean empty = false;
			if(beads == 0 && owner.equals(thisTurnsPlayer)) {
				empty = true;
			}
		return empty;
	}
}
