package nl.sogyo.mancala.domain;

public abstract class AbstractBowl {
    int beads;
    Player owner;
    AbstractBowl neighbour;

    AbstractBowl() {
        this.beads = 0;
        this.owner = null;
        this.neighbour = null;
    }

    // -- Getters --
    int getBeads() {
        return beads;
    }

    Player getOwner() {
        return owner;
    }

    AbstractBowl getNeighbour() {
        return neighbour;
    }

    AbstractBowl getNeighbour(int amount) {
        if (amount > 0) {
            return neighbour.getNeighbour(amount - 1);
        }
        return this;
    }

    // --- Abstract Methods ---
    protected abstract int takeBead(int beadsInHand);

    protected abstract void distribute(int beadsInHand, Player thisTurnsPlayer);

    protected abstract boolean checkBowlsEmpty();

    protected abstract AbstractBowl findOpposite();

    protected abstract void putBeadsInKalaha(int beads, Player thisTurnsPlayer);

    protected abstract Kalaha findOwnKalaha(Player thisTurnsPlayer);

    protected abstract Kalaha findOpponentKalaha(Player thisTurnsPlayer);

    // --- AbstractBowl Methods ---
    boolean isAllEmpty(Player thisTurnsPlayer) {
        findOpponentKalaha(thisTurnsPlayer);
        AbstractBowl firstBowl = findOpponentKalaha(thisTurnsPlayer).getNeighbour();
        return firstBowl.checkBowlsEmpty();
    }

    Player getWinner() {
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
