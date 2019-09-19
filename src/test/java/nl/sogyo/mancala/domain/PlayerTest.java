package nl.sogyo.mancala.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.sogyo.mancala.Bowl;
import nl.sogyo.mancala.Kalaha;
import nl.sogyo.mancala.Player;

public class PlayerTest {
	

	@Test
	public void opponentTest() {
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		Player player2 = playerList[1];
		
		assertEquals("Player 1 should have player 2 as its opponent.", player1, player2.getOpponent());
		assertEquals("Player 2 should have player 1 as its opponent.", player2, player1.getOpponent());
	}
	
	
	@Test
	public void startingPlayerTest() {
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		Player player2 = playerList[1];
		
		assertEquals("Player 1 should have the first, starting turn." , player1.getPlayerTurn(), true);
		assertEquals("Player 2 should not have the starting turn." ,player2.getPlayerTurn(), false);
	}
	
	
	@Test
	public void playerTurnIfBowl() {
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		Player player2 = playerList[1];
		Bowl bowl = new Bowl();
		bowl.setOwner(player1);
		bowl.orderPlayerChange();

		
		assertEquals("Player 1 should be not have his turn again.", player1.getPlayerTurn(), false);
		assertEquals("Player 2 should be now have his turn.", player2.getPlayerTurn(), true);
	}
	
	
	@Test
	public void playerTurnIfKalaha() {
		Player[] playerList = Player.makePlayers();
		Player player1 = playerList[0];
		Player player2 = playerList[1];
		Kalaha kalaha = new Kalaha();
		kalaha.setOwner(player1);
		
		
		assertEquals("Player 1 should have another turn.", player1.getPlayerTurn(), true);
		assertEquals("Player 2 should not have the turn", player2.getPlayerTurn(), false);
	}
}
