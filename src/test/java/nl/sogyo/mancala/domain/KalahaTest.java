package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.Bowl;
import nl.sogyo.mancala.Kalaha;

public class KalahaTest {

	
	@Test 
	public void kalahaSpawnsEmptyTest() {
	Bowl bowl = new Bowl(4, 1);
		
		assertEquals("Kalaha is not empty.", 0, bowl.getNeighbour(1).getBeads());
		assertEquals("Kalaha is not empty.", 0, bowl.getNeighbour(3).getBeads());
	}
	
	
	@Test
	public void distributeBowlAlongKahalaTest() {
		Bowl bowl = new Bowl(4, 1);
		int beadsInHand = 3;
		
		bowl.distribute(beadsInHand, bowl.getOwner());
		
		assertEquals(5, bowl.getBeads());
		assertEquals(1, bowl.getNeighbour(1).getBeads());
		assertEquals(5, bowl.getNeighbour(2).getBeads());
	}
	
	
	@Test
	public void distributeBowlAlongOpponentKahalaTest() {
		Bowl bowl = new Bowl(4, 1);
		int beadsInHand = 4;
		bowl.distribute(beadsInHand, bowl.getOwner());
		
		assertEquals(6, bowl.getBeads());
		assertEquals(1, bowl.getNeighbour(1).getBeads());
		assertEquals(5, bowl.getNeighbour(2).getBeads());
		assertEquals(0, bowl.getNeighbour(3).getBeads());
	}
	
	
	@Test
	public void putBeadsInKalahaTest() {
	Bowl bowl = new Bowl(4, 6);
			
		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.startDistribute();
		
		assertEquals(0, bowl.getBeads());
		assertEquals(0, bowl.getNeighbour(4).getBeads());
		assertEquals("Kalaha did not receive the bead.", 5, ((Kalaha) bowl.getNeighbour(6)).getBeads());		
	}

	
	@Test
	public void putOpponentBeadsInKalahaTest() {
	Bowl bowl = new Bowl(4, 6);	
	
		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.startDistribute();
		
		assertEquals("Kalaha did not receive the beads.", 5, ((Kalaha) bowl.getNeighbour(6)).getBeads());
	}
	
	
	@Test
	public void getOwnerWinnerFromKalahaTest() {
		Bowl bowl = new Bowl(4, 6);	
		
		bowl.getNeighbour(6).setBeads(4);
		
		assertEquals("Player 1 is not the winner while it should be.", bowl.getOwner(), bowl.getNeighbour(6).getWinner());
	}
	
	
	@Test
	public void getWinnerOpponentFromKalahaTest() {
		Bowl bowl = new Bowl(4, 6);	

		bowl.getNeighbour(13).setBeads(4);
		
		assertEquals("Player 2 is not the winner while it should be.", bowl.getOwner().getOpponent(), bowl.getNeighbour(6).getWinner());
	}
	
	
	@Test
	public void getWinnerTieFromKalahaTest() {
		Bowl bowl = new Bowl(4, 6);	
		
		assertEquals("There should be no winner (null).", null, bowl.getNeighbour(6).getWinner());
	}
	
	
	@Test
	public void isAllEmptyFromKalahaTest() {
		Bowl bowl = new Bowl(0, 6);
		
		assertTrue("One of the bowls are not empty, received false.", bowl.getNeighbour(6).isAllEmpty(bowl.getOwner()));
	}

	
	@Test
	public void isOneNotEmptyFromKalahaTest() {
		Bowl bowl = new Bowl(0, 6);
		
		bowl.getNeighbour(3).setBeads(3);
		
		assertFalse("All the bowls are empty, received true.", bowl.getNeighbour(6).isAllEmpty(bowl.getOwner()));
	}
}
