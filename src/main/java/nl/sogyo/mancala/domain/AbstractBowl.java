package nl.sogyo.mancala.domain;

public abstract class AbstractBowl {
	protected int beads;
	protected Player owner;
	protected AbstractBowl neighbour;

	public AbstractBowl() {
		this.beads = 0;
		this.owner = null;
		this.neighbour = null;
	}

	// -- Getters --
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
		while (amount > 0) {
			return neighbour.getNeighbour(amount - 1);
		}
		return this;
	}

	// -- Setters --
	public void setBeadsForTest(int beads) {
		this.beads = beads;
	}

	// AbstractBowl Abstract Methods
	protected abstract int takeBead(int beadsInHand);

	protected abstract void distribute(int beadsInHand, Player thisTurnsPlayer);

	protected abstract boolean checkBowlsEmpty();

	protected abstract AbstractBowl findOpposite();

	protected abstract void putBeadsInKalaha(int beads, Player thisTurnsPlayer);

	// AbstractBowl Methods
	protected boolean isAllEmpty(Player thisTurnsPlayer) {
		findOpponentKalaha(thisTurnsPlayer);
		AbstractBowl firstBowl = findOpponentKalaha(thisTurnsPlayer).getNeighbour();
		return firstBowl.checkBowlsEmpty();
	}

	protected Kalaha findOwnKalaha(Player thisTurnsPlayer) {
		while (neighbour instanceof Kalaha && neighbour.getOwner().equals(thisTurnsPlayer)) {
			return (Kalaha) neighbour;
		}
		return neighbour.findOwnKalaha(thisTurnsPlayer);
	}

	protected Kalaha findOpponentKalaha(Player thisTurnsPlayer) {
		while (neighbour instanceof Kalaha && neighbour.getOwner().equals(thisTurnsPlayer.getOpponent())) {
			return (Kalaha) neighbour;
		}
		return neighbour.findOpponentKalaha(thisTurnsPlayer);
	}

	public Player getWinner() {
		Kalaha ownKalaha = findOwnKalaha(this.owner);
		Kalaha opponentKalaha = findOpponentKalaha(this.owner);

		if (ownKalaha.getBeads() > opponentKalaha.getBeads()) {
			return ownKalaha.getOwner();
		} else if (opponentKalaha.getBeads() > ownKalaha.getBeads()) {
			return opponentKalaha.getOwner();
		}
		return null;
	}
}
