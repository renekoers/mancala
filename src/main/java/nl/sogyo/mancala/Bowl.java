package nl.sogyo.mancala;

public class Bowl extends AbstractBowl {
	
	
	// --- Constructors ---
	public Bowl() {
		super();
		this.beads = 4;
	}
	
	
	// Create a bowl, give it an owner and make new bowls for amount of bowls input.
	public Bowl(int beadsInBowls, int amountOfBowls) {
		super();
		this.beads = beadsInBowls;
		this.owner = new Player();
		if (amountOfBowls > 1) {
			this.neighbour = new Bowl(beadsInBowls, amountOfBowls, amountOfBowls-=1, owner, this);
		} else { 
			this.neighbour = new Kalaha(beadsInBowls, amountOfBowls, owner, this);
		}
	}
	
	
	// For each bowl that's made the remaining bowls declines until no more are made.
	public Bowl(int beadsInBowls, int amountofBowls, int remainingBowls, Player owner, Bowl firstBowl) {
		this.beads = beadsInBowls;
		this.owner = owner;
		if (remainingBowls > 1) {
			this.neighbour = new Bowl(beadsInBowls, amountofBowls, remainingBowls-=1, owner, firstBowl);
		} else { 
			this.neighbour = new Kalaha(beadsInBowls, amountofBowls, owner, firstBowl);
		}
	}
	

	// --- Bowl methods ---
	public void orderPlayerChange() {
		owner.changeTurn();
	}
	
	
	public int takeBead(int beadsInHand) {
		beads += 1;
		beadsInHand -= 1;
		return beadsInHand;
	}
	
	
	public void distribute(int beadsInHand, Player thisTurnsPlayer) {
		beadsInHand = takeBead(beadsInHand);
		if (beadsInHand > 0) {
			neighbour.distribute(beadsInHand, thisTurnsPlayer);
		} else if (this.wasEmpty() == true){
			putBeadsInKalaha(beads, owner);
			beads = 0;
			
			// Find opposite bowl and order it to put its beads into the current kalaha.
			Bowl opposite = findOpposite();
			opposite.putBeadsInKalaha(opposite.getBeads(), thisTurnsPlayer);
			opposite.emptyBowl();

			owner.changeTurn();
		} else {
			owner.changeTurn();
		}
	}

	
	public void startDistribute() {
		int grabbedBeads = beads;
		beads = 0;
		neighbour.distribute(grabbedBeads, owner);
	}
	
	
	public void emptyBowl() {
		beads = 0;
	}
	
	
	protected boolean checkBowlsEmpty(){
		if(beads == 0) {
			return neighbour.checkBowlsEmpty();
		} else {
			return false;
		}
	}
	
	
	public boolean wasEmpty() {
		if (this.beads == 1) {
			return true;
		}
		return false;
	}
	
	
	public void putBeadsInKalaha (int beads, Player thisTurnsPlayer) {
			neighbour.putBeadsInKalaha(beads, thisTurnsPlayer);
		}
	
	
	public Bowl findOpposite() {
		return (Bowl) neighbour.findOpposite().neighbour;
	}
}
