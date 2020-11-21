package es.damas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PawnTest {
	
	private Pawn pawn = new Pawn(Color.BLACK);	
	
	private static char[] CHARACTERS = { 'b', 'n' };
	
	private Coordinate [] coordinatesCase1 = {new Coordinate(1, 1),new Coordinate(2, 2)};
	
	private Coordinate [] coordinatesCase2 = {new Coordinate(1, 1),new Coordinate(1, 1)};
	
	private Coordinate [] coordinatesCase3 = {new Coordinate(1, 1),new Coordinate(10, 10)};
	
	private Coordinate [] coordinatesCase4 = {new Coordinate(1, 1),new Coordinate(3, 3)};

	
	@Test
	public void testWhengetCodesThenIsCorrect() {
		char[] chars = pawn.getCodes();
		assertTrue((CHARACTERS[0] == chars[0]) && (CHARACTERS[1] == chars[1]));
	}
	
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectDiagonalMovementThenIsCorrect() {
		assertEquals(null, pawn.isCorrectDiagonalMovement(0, 0, coordinatesCase1));
	}
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectDiagonalMovementThenErrorNotAdvanced() {
		assertEquals(Error.NOT_ADVANCED, pawn.isCorrectDiagonalMovement(0, 0, coordinatesCase2));
	}
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectDiagonalMovementThenErrorToMuchAdvanced() {
		assertEquals(Error.TOO_MUCH_ADVANCED, pawn.isCorrectDiagonalMovement(0, 0, coordinatesCase3));
	}
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectDiagonalMovementThenErrorWithoutEating() {
		assertEquals(Error.WITHOUT_EATING, pawn.isCorrectDiagonalMovement(0, 0, coordinatesCase4));
	}	

}
