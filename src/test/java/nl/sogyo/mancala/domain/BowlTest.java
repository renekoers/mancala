package nl.sogyo.mancala.domain;


import static org.junit.Assert.*;
import org.junit.Test;



public class BowlTest {

	
	@Test
	public void giveBowlNeighbourTest() {
		Bowl bowl = new Bowl(4, 2);

		assertNotNull(bowl);
		assertTrue("The neighbouring bowl is not a bowl.", bowl.getNeighbour() instanceof Bowl);
	}
	
	
	@Test
	public void giveKalahaNeighbourTest() {
		Bowl bowl = new Bowl(4, 1);
		
		assertNotNull(bowl);
		assertTrue("The neightbouring bowl is not a kalaha.", bowl.getNeighbour() instanceof Kalaha);
	}

	
	@Test
	public void checkIfBoardGoesRoundTest() {
		Bowl bowl = new Bowl(4, 6);
		
		assertEquals("The first bowl is not the neighbour of the last bowl.", bowl.getNeighbour(14), bowl);
	}
	
	@Test
	public void makeBowlWithOwner() {
		Bowl bowl = new Bowl(4, 1);
		
		assertNotNull("The bowl has no owner.", bowl.getOwner());
	}
	
	
	@Test
	public void getNeighbourTest() {
		Bowl bowl = new Bowl(4, 2);
		
		assertEquals(bowl.getNeighbour(), bowl.getNeighbour(1));
	}
	
	
	@Test
	public void takeBeadTest() {
		Bowl bowl = new Bowl(4, 2);
		bowl.play();
		
		assertEquals(5, bowl.getNeighbour().getBeads());
	}
	
	
	@Test
	public void distributeBowlToBowlTest() {
		Bowl bowl = new Bowl(4, 2);
		bowl.play();
		
		assertEquals(0, bowl.getBeads());
		assertEquals(5, bowl.getNeighbour().getBeads());

	}
	
	
	@Test
	public void distributeBowlAlongKahalaTest() {
		Bowl bowl = new Bowl(2, 1);
		bowl.play();

		assertEquals(0, bowl.getBeads());
		assertEquals(1, bowl.getNeighbour(1).getBeads());
		assertEquals(3, bowl.getNeighbour(2).getBeads());
	}

	@Test
	public void distributeBowlAlongOpponentKahalaTest() {
		Bowl bowl = new Bowl(4, 1);
		bowl.play();

		assertEquals(1, bowl.getBeads());
		assertEquals(2, bowl.getNeighbour(1).getBeads());
		assertEquals(5, bowl.getNeighbour(2).getBeads());
		assertEquals(0, bowl.getNeighbour(3).getBeads());
	}
	
	
	@Test
	public void startDistributeTest() {
		Bowl bowl = new Bowl(4, 6);
		bowl.play();
		
		assertEquals(0, bowl.getBeads());
		assertEquals(5, bowl.getNeighbour(1).getBeads());
		assertEquals(5, bowl.getNeighbour(2).getBeads());
		assertEquals(5, bowl.getNeighbour(3).getBeads());
		assertEquals(5, bowl.getNeighbour(4).getBeads());
		assertEquals(4, bowl.getNeighbour(5).getBeads());
	}
	
	
	@Test
	public void findOpponentKalaha() {
		Bowl bowl = new Bowl(4, 6);
		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.play();

		assertNotNull("The bowl returned is not the enemy kalaha.", bowl.findOpponentKalaha(bowl.getOwner()));
		assertEquals("The player returned is not the opponent", bowl.getOwner().getOpponent(), bowl.findOpponentKalaha(bowl.getOwner()).getOwner());
	}
	
	
	@Test
	public void isAllEmptyTest() {
		Bowl bowl = new Bowl(0, 6);
		
		assertTrue("One of the bowls are not empty, received false.", bowl.isAllEmpty(bowl.getOwner()));
	}

	
	@Test
	public void isOneNotEmptyTest() {
		Bowl bowl = new Bowl(2, 2);
		
		bowl.play();
		
		assertFalse("All the bowls are empty, received true.", bowl.isAllEmpty(bowl.getOwner()));
	}

	@Test
	public void wasEmptyTest() {
		Bowl bowl = new Bowl(4, 6);
		
		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.play();
		
		assertEquals(0, bowl.getBeads());
		assertEquals(0, ((Bowl) bowl.getNeighbour(4)).getBeads());
	}
	
	
	@Test
	public void wasNotEmptyTest() {
		Bowl bowl = new Bowl(4, 6);
		
		bowl.play();
		
		assertFalse("The last bowl was empty.", ((Bowl) bowl.getNeighbour(4)).wasEmpty());
	}
	
	@Test
	public void putBeadsInKalahaTest() {
	Bowl bowl = new Bowl(4, 6);
			
		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.play();
		
		assertEquals(0, bowl.getBeads());
		assertEquals(0, bowl.getNeighbour(4).getBeads());
		assertEquals("Kalaha did not receive the bead.", 5, ((Kalaha) bowl.getNeighbour(6)).getBeads());		
	}
	
	
	@Test
	public void findOppositeTest() {
	Bowl bowl = new Bowl(4, 6);
	Bowl opposite = bowl.findOpposite();
		assertEquals("Did not find the opposite.", bowl.getNeighbour(12), opposite);
	}
	
	
	@Test
	public void putOpponentBeadsInKalahaTest() {
	Bowl bowl = new Bowl(4, 6);	
	
		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.play();
		
		assertEquals("Kalaha did not receive the beads.", 5, ((Kalaha) bowl.getNeighbour(6)).getBeads());
	}
	
	
	@Test
	public void getOwnerWinnerTest() {
		Bowl bowl = new Bowl(4, 2);

		bowl.play();
		
		assertEquals("Player 1 is not the winner while it should be.", bowl.getOwner(), bowl.getWinner());
	}
	
	
	@Test
	public void getWinnerOpponentTest() {
		Bowl bowl = new Bowl(2, 3);
		bowl.play();
		((Bowl)bowl.getNeighbour(5)).play();
		
		assertEquals("Player 2 is not the winner while it should be.", bowl.getOwner().getOpponent(), bowl.getWinner());
	}
	
	
	@Test
	public void getWinnerTieTest() {
		Bowl bowl = new Bowl(4, 6);

		assertNull("There should be no winner (null).", bowl.getWinner());
	}
	
	
	@Test
	public void playBowlWithPlayMethodTest() {
		Bowl bowl = new Bowl(4, 6);
		
		((Bowl)bowl.getNeighbour(3)).play();

		assertEquals("Bowl 4 has not been played", 0, bowl.getNeighbour(3).getBeads());
		assertEquals("Kalaha didn't receive a bead into.", 1, bowl.getNeighbour(6).getBeads());
		assertEquals("Bowl 8 didn't get a bead.", 5, bowl.getNeighbour(7).getBeads());
		assertEquals("Bowl 9 did get a bead.", 4, bowl.getNeighbour(8).getBeads());
		assertFalse("Player turn did not switch at end of the turn.", bowl.getOwner().hasTurn());
	}
}
