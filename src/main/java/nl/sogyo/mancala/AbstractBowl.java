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
	
	
	public AbstractBowl getNeighbour(int amount) {
		if (amount > 0) {
			return neighbour.getNeighbour(amount - 1); 
		} else {
			return this;
		}
	}
	 
	// -- Setters --
	public void setOwner(Player player) {
		owner = player;
	}
	
	public void setBeads(int beads) {
		this.beads = beads;
	}
	 
	
	// AbstractBowl Abstract Methods
	protected abstract int takeBead(int beadsInHand);
	 
	 
	public abstract void distribute(int beadsInHand, Player thisTurnsPlayer);

	
	protected abstract boolean checkBowlsEmpty();

	
	protected abstract AbstractBowl findOpposite();
	
	
	protected abstract void putBeadsInKalaha(int beads, Player thisTurnsPlayer);
	
	
	// AbstractBowl Methods
	public boolean isAllEmpty(Player thisTurnsPlayer) {
		findOpponentKalaha(thisTurnsPlayer);
		AbstractBowl firstBowl = findOpponentKalaha(thisTurnsPlayer).getNeighbour();
		return firstBowl.checkBowlsEmpty();
	}
	
	
	public Kalaha findOwnKalaha(Player thisTurnsPlayer) {
		if (neighbour instanceof Kalaha && neighbour.getOwner().equals(thisTurnsPlayer)) {
			return (Kalaha) neighbour;
		} else {
			return neighbour.findOwnKalaha(thisTurnsPlayer);
		}
	}
	
	
	public Kalaha findOpponentKalaha(Player thisTurnsPlayer) {
		if (neighbour instanceof Kalaha && neighbour.getOwner().equals(thisTurnsPlayer.getOpponent())) {
			return (Kalaha) neighbour;
		} else {
			return neighbour.findOpponentKalaha(thisTurnsPlayer);
		}
	}
	 
	
	public Player getWinner() {
		Kalaha ownKalaha = findOwnKalaha(this.owner);
		Kalaha opponentKalaha = findOpponentKalaha(this.owner);
		
		if (ownKalaha.getBeads() > opponentKalaha.getBeads()) {
			return ownKalaha.getOwner();
		} else if (opponentKalaha.getBeads() > ownKalaha.getBeads()) {
			return opponentKalaha.getOwner();
		} else {
			return null;
		}
	}
}
