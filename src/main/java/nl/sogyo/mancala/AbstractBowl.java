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
	 
	 
//	 protected abstract int putBead();
//	 
//	 
//	 protected abstract void distribute();
//	 
//	 
//	 protected abstract boolean isAllEmpty();
//	 
//	 
//	 protected abstract void putBeadsInKalaha();
//	 
//	 
//	 protected abstract AbstractBowl findOpposite();
//	 
//	 
//	 protected abstract Player getWinner();
}
