package nl.sogyo.mancala.domain;

public class Bowl extends AbstractBowl {

    // --- Constructor ---
    // Create a bowl, give it an owner and make new bowls for amount of bowls input.
    Bowl(int beadsInBowls, int amountOfBowls) {
        super();
        this.beads = beadsInBowls;
        this.owner = new Player();
        if (amountOfBowls > 1) {
            this.neighbour = new Bowl(beadsInBowls, amountOfBowls, amountOfBowls - 1, owner, this);
        } else {
            this.neighbour = new Kalaha(beadsInBowls, amountOfBowls, owner, this);
        }
    }

    // For each bowl that's made the remaining bowls declines until no more are
    // made.
    Bowl(int beadsInBowls, int amountofBowls, int remainingBowls, Player owner, Bowl firstBowl) {
        this.beads = beadsInBowls;
        this.owner = owner;
        if (remainingBowls > 1) {
            this.neighbour = new Bowl(beadsInBowls, amountofBowls, remainingBowls -= 1, owner, firstBowl);
        } else {
            this.neighbour = new Kalaha(beadsInBowls, amountofBowls, owner, firstBowl);
        }
    }

    // --- Setter ---
	void emptyBowl() {
        beads = 0;
    }

    // --- Bowl methods ---
    private void orderPlayerChange() {
        owner.changeTurn();
    }

    protected int takeBead(int beadsInHand) {
        beads += 1;
        beadsInHand -= 1;
        return beadsInHand;
    }

    protected void distribute(int beadsInHand, Player thisTurnsPlayer) {
        beadsInHand = takeBead(beadsInHand);
        if (beadsInHand > 0) {
            neighbour.distribute(beadsInHand, thisTurnsPlayer);
        } else if (wasEmpty()) {
            putBeadsInKalaha(beads, owner);
            beads = 0;

            Bowl opposite = findOpposite();
            opposite.putBeadsInKalaha(opposite.getBeads(), thisTurnsPlayer);
            opposite.emptyBowl();

            orderPlayerChange();
        } else {
            orderPlayerChange();
        }
    }

    void play() {
        int grabbedBeads = beads;
        beads = 0;
        neighbour.distribute(grabbedBeads, owner);
    }

    protected boolean checkBowlsEmpty() {
        if (beads == 0) {
            return neighbour.checkBowlsEmpty();
        }
        return false;
    }

    boolean wasEmpty() {
		return beads == 1;
	}

    protected void putBeadsInKalaha(int beads, Player thisTurnsPlayer) {
        neighbour.putBeadsInKalaha(beads, thisTurnsPlayer);
    }

    protected Kalaha findOwnKalaha(Player thisTurnsPlayer) {
        return neighbour.findOwnKalaha(thisTurnsPlayer);
    }

	protected Kalaha findOpponentKalaha(Player thisTurnsPlayer) {
		return neighbour.findOpponentKalaha(thisTurnsPlayer);
	}

    protected Bowl findOpposite() {
        return (Bowl) neighbour.findOpposite().neighbour;
    }
}
