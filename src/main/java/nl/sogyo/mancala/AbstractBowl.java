package nl.sogyo.mancala;

public abstract class AbstractBowl {
	protected int beads;
	protected Player owner;
	protected AbstractBowl neighbour;
	
	public AbstractBowl() {
		this.beads = 0;
		this.owner = null;
		this.neighbour = null;
	}
	
	
	protected boolean isOwnerTurn() {
		return owner.getMyTurn();
	}
	 

	//-- Getters --
	public int getBeads() {
		return beads;
	}
	 
	 
	public Player getOwner() {
		return owner;
	}
	 
	 
	public AbstractBowl getNeighbour() {
		return neighbour;
	}
	 
	 
	// -- Setters --
	public void setOwner(Player player) {
		owner = player;
	}
	 
	
	public void giveBowlNeighbour() {
		Bowl bowl = new Bowl();
		neighbour = bowl;
	}
	

	public void giveKalahaNeighbour() {
		Kalaha kalaha = new Kalaha();
		neighbour = kalaha;
	}
	 
	
	// AbstractBowl Main Methods
	protected abstract int takeBead(int beadsInHand);
	 
	 
	public void distribute(int beadsInHand, Player thisTurnsPlayer) {
		beadsInHand = takeBead(beadsInHand);
		if (beadsInHand > 0) {
			neighbour.distribute(beadsInHand, thisTurnsPlayer);
		}
	}

	
	public Kalaha findOpponentKalaha(Player thisTurnsPlayer) {
		if (neighbour instanceof Kalaha && neighbour.getOwner().equals(thisTurnsPlayer.getOpponent())) {
			return (Kalaha) neighbour;
		} else {
			return neighbour.findOpponentKalaha(thisTurnsPlayer);
		}
	}
	

//	protected abstract boolean isAllEmpty();
//	 
//	 
//	protected abstract void putBeadsInKalaha();
//	 
//	 
//	protected abstract AbstractBowl findOpposite();
//	 
//	 
//	protected abstract Player getWinner();
}
