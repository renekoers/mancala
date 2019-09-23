package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.Bowl;
import nl.sogyo.mancala.Player;

public class PlayerTest {
	

	@Test
	public void makePlayerPairTest() {
		final Player player1 = new Player();
		final Player player2 = player1.getOpponent();
		
		assertEquals("Player 1 should have player 2 as its opponent.", player1, player2.getOpponent());
		assertEquals("Player 2 should have player 1 as its opponent.", player2, player1.getOpponent());
	}
	
	
	@Test
	public void startingPlayerTest() {
		final Player player1 = new Player();
		final Player player2 = player1.getOpponent();
		
		assertEquals("Player 1 should have the first, starting turn." , player1.getMyTurn(), true);
		assertEquals("Player 2 should not have the starting turn." ,player2.getMyTurn(), false);
	}
	
	
	@Test
	public void playerTurnIfBowl() {
		final Player player1 = new Player();
		final Player player2 = player1.getOpponent();
		Bowl bowl = new Bowl();
		bowl.setOwner(player1);
		bowl.orderPlayerChange();

		
		assertEquals("Player 1 should be not have his turn again.", player1.getMyTurn(), false);
		assertEquals("Player 2 should be now have his turn.", player2.getMyTurn(), true);
	}
	

	// Currently Kalaha does not interact with playerturn.
//	@Test
//	public void playerTurnIfKalaha() {
//		final Player player1 = new Player();
//		final Player player2 = player1.getOpponent();
//		Kalaha kalaha = new Kalaha();
//		
//		assertEquals("Player 1 should have another turn.", player1.getMyTurn(), true);
//		assertEquals("Player 2 should not have the turn", player2.getMyTurn(), false);
//	}
}
