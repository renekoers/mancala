package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class KalahaTest {

	@Test
	public void kalahaSpawnsEmptyTest() {
		Bowl bowl = new Bowl(4, 1);

		assertEquals("Kalaha is not empty.", 0, bowl.getNeighbour(1).getBeads());
		assertEquals("Kalaha is not empty.", 0, bowl.getNeighbour(3).getBeads());
	}

	@Test
	public void distributeBowlAlongKahalaTest() {
		Bowl bowl = new Bowl(2, 1);
		bowl.play(1);

		assertEquals(0, bowl.getBeads());
		assertEquals(1, bowl.getNeighbour(1).getBeads());
		assertEquals(3, bowl.getNeighbour(2).getBeads());
	}

	@Test
	public void distributeBowlAlongOpponentKahalaTest() {
		Bowl bowl = new Bowl(4, 1);
		bowl.play(1);

		assertEquals(1, bowl.getBeads());
		assertEquals(2, bowl.getNeighbour(1).getBeads());
		assertEquals(5, bowl.getNeighbour(2).getBeads());
		assertEquals(0, bowl.getNeighbour(3).getBeads());
	}

	@Test
	public void putBeadsInKalahaTest() {
		Bowl bowl = new Bowl(4, 6);

		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.play(1);

		assertEquals(0, bowl.getBeads());
		assertEquals(0, bowl.getNeighbour(4).getBeads());
		assertEquals("Kalaha did not receive the bead.", 5, ((Kalaha) bowl.getNeighbour(6)).getBeads());
	}

	@Test
	public void putOpponentBeadsInKalahaTest() {
		Bowl bowl = new Bowl(4, 6);

		((Bowl) bowl.getNeighbour(4)).emptyBowl();
		bowl.play(1);

		assertEquals("Kalaha did not receive the beads.", 5, ((Kalaha) bowl.getNeighbour(6)).getBeads());
	}

	@Test
	public void getOwnerWinnerFromKalahaTest() {
		Bowl bowl = new Bowl(4, 6);

		bowl.getNeighbour(6).setBeadsForTest(4);

		assertEquals("Player 1 is not the winner while it should be.", bowl.getOwner(),
				bowl.getNeighbour(6).getWinner());
	}

	@Test
	public void getWinnerOpponentFromKalahaTest() {
		Bowl bowl = new Bowl(4, 6);

		bowl.getNeighbour(13).setBeadsForTest(4);

		assertEquals("Player 2 is not the winner while it should be.", bowl.getOwner().getOpponent(),
				bowl.getNeighbour(6).getWinner());
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

		bowl.getNeighbour(3).setBeadsForTest(3);

		assertFalse("All the bowls are empty, received true.", bowl.getNeighbour(6).isAllEmpty(bowl.getOwner()));
	}
}
