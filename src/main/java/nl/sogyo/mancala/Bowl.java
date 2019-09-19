package nl.sogyo.mancala;

public class Bowl extends AbstractBowl {

	public Bowl() {
		super();
		this.beads = 4;
	}
	
	
	public void setOwner(Player player) {
		owner = player;
	}
	
	
	public void orderPlayerChange() {
		owner.changeTurn();
	}
	
	
	
}
