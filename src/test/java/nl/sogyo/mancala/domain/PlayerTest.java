package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void makePlayerPairTest() {
		Bowl bowl = new Bowl(4, 6);
		final Player player1 = bowl.getOwner();
		final Player player2 = bowl.getOwner().getOpponent();

		assertEquals("Player 1 should have player 2 as its opponent.", player1.getOpponent(), player2);
		assertEquals("Player 2 should have player 1 as its opponent.", player2.getOpponent(), player1);
	}

	@Test
	public void startingPlayerTest() {
		Bowl bowl = new Bowl(4, 6);

		assertTrue("Player 1 should have the first, starting turn.", bowl.getOwner().hasTurn());
		assertFalse("Player 2 should not have the starting turn.", bowl.getOwner().getOpponent().hasTurn());
	}

	@Test
	public void playerTurnIfBowl() {
		Bowl bowl = new Bowl(4, 6);
		bowl.play();

		assertFalse("Player 1 should be not have his turn again.", bowl.getOwner().hasTurn());
		assertTrue("Player 2 should be now have his turn.", bowl.getOwner().getOpponent().hasTurn());
	}
}
