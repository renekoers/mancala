package nl.sogyo.mancala.domain;


import static org.junit.Assert.*;
import org.junit.Test;
import nl.sogyo.mancala.Bowl;
import nl.sogyo.mancala.Kalaha;
import nl.sogyo.mancala.Player;



public class BowlTest {

	
	@Test
	public void giveBowlNeighbourTest() {
		Bowl bowl = new Bowl();
		
		bowl.giveBowlNeighbour();
		
		assertNotNull(bowl);
		assertTrue("The neighbouring bowl is not a bowl.", bowl.getNeighbour() instanceof Bowl);
	}
	
	
	@Test
	public void giveKalahaNeighbourTest() {
		Bowl bowl = new Bowl();
		
		bowl.giveKalahaNeighbour();
		
		assertNotNull(bowl);
		assertTrue("The neightbouring bowl is not a kalaha.", bowl.getNeighbour() instanceof Kalaha);
	}

	
	@Test
	public void makeBowlWithOwner() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		
		bowl.setOwner(player1);
		
		assertNotNull("The bowl has no owner.", bowl.getOwner());
	}
	
	
	@Test
	public void takeBeadTest() {
		Bowl bowl = new Bowl();
		int beadsInHand = 5;
		
		beadsInHand = bowl.takeBead(beadsInHand);
		
		assertEquals(4, beadsInHand);
		assertEquals(5, bowl.getBeads());
	}
	
	
	@Test
	public void distributeBowlToBowlTest() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		int beadsInHand = 2;
		
		bowl.setOwner(player1);
		bowl.giveBowlNeighbour();
		bowl.getNeighbour().giveBowlNeighbour();
		bowl.distribute(beadsInHand, player1);
		
		assertEquals(5, bowl.getBeads());
		assertEquals(5, bowl.getNeighbour().getBeads());
		assertEquals(4, bowl.getNeighbour().getNeighbour().getBeads());
	}
	
	
	@Test
	public void distributeBowlAlongKahalaTest() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		int beadsInHand = 3;
		
		bowl.setOwner(player1);
		bowl.giveKalahaNeighbour();
		bowl.getNeighbour().setOwner(player1);
		bowl.getNeighbour().giveBowlNeighbour();
		bowl.distribute(beadsInHand, player1);
		
		assertEquals(5, bowl.getBeads());
		assertEquals(1, bowl.getNeighbour().getBeads());
		assertEquals(5, bowl.getNeighbour().getNeighbour().getBeads());
		
	}
	
	
	@Test
	public void distributeBowlAlongOpponentKahalaTest() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		Player player2 = playerList[1];
		int beadsInHand = 2;

		bowl.setOwner(player1);
		bowl.giveKalahaNeighbour();
		bowl.getNeighbour().setOwner(player2);
		bowl.getNeighbour().giveBowlNeighbour();
		bowl.distribute(beadsInHand, player1);
		
		assertEquals(5, bowl.getBeads());
		assertEquals(0, bowl.getNeighbour().getBeads());
		assertEquals(5, bowl.getNeighbour().getNeighbour().getBeads());
	}
	
	
	@Test
	public void startDistributeTest() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		
		bowl.setOwner(player1);
		bowl.giveBowlNeighbour();
		bowl.getNeighbour().giveBowlNeighbour();
		bowl.getNeighbour().getNeighbour().giveBowlNeighbour();
		bowl.getNeighbour().getNeighbour().getNeighbour().giveBowlNeighbour();
		bowl.startDistribute();
		
		assertEquals(0, bowl.getBeads());
		assertEquals(5, bowl.getNeighbour().getBeads());
		assertEquals(5, bowl.getNeighbour().getNeighbour().getBeads());
		assertEquals(5, bowl.getNeighbour().getNeighbour().getNeighbour().getBeads());
		assertEquals(5, bowl.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getBeads());
	}
	
	
	@Test
	public void findOpponentKalaha() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		Player player2 = playerList[1];
		
		bowl.setOwner(player1);
		bowl.giveBowlNeighbour();
		bowl.getNeighbour().setOwner(player1);
		bowl.getNeighbour().giveKalahaNeighbour();
		bowl.getNeighbour().getNeighbour().setOwner(player2);
		
		assertTrue("The bowl returned is not the enemy kalaha.", bowl.findOpponentKalaha(player1) instanceof Kalaha);
		assertEquals("The player returned is not the opponent", player1.getOpponent(), bowl.findOpponentKalaha(player1).getOwner());
	}
	
	
	@Test
	public void isAllEmptyTest() {
		Bowl bowl = new Bowl();
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		
		bowl.emptyBowl();
		bowl.setOwner(player1);
		bowl.giveBowlNeighbour();
		((Bowl) bowl.getNeighbour()).emptyBowl();
		bowl.getNeighbour().giveBowlNeighbour();
		((Bowl) bowl.getNeighbour().getNeighbour()).emptyBowl();
		
		assertTrue("One of the bowls are not empty, received false.", bowl.isAllEmpty(player1));

	}

	
	@Test
	public void wasEmptyTest() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void putBeadsInKalahaTest() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void findOppositeTest() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void getWinner() {
		fail("Not yet implemented");
	}
}
