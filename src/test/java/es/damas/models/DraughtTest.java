package es.damas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DraughtTest {
	
	private Draught draught = new Draught(Color.WHITE);	
	
	private String code = "B";
	
	private Coordinate [] coordinatesCase1 = {new Coordinate(0, 0),new Coordinate(1, 1)};
	
	private Coordinate [] coordinatesCase2 = {new Coordinate(0, 0),new Coordinate(2, 1)};
	
	private Draught draught2 = new Draught(Color.BLACK);	
	
	private List<Piece> pieces = new ArrayList<>();
	
	@Test
	public void testWhengetCodesThenIsCorrect() {
		assertEquals(code, draught.getCode());
	}	
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectDiagonalMovementThenIsCorrect() {
		assertEquals(null, draught.isCorrectDiagonalMovement(0, 0, coordinatesCase1));
	}
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectMovementThenIsCorrect() {
		pieces.clear();
		pieces.add(draught2);
		assertEquals(null, draught.isCorrectMovement(pieces, 0, coordinatesCase1));
	}
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectMovementThenErrorNotDiagonal() {
		pieces.clear();
		pieces.add(draught2);
		assertEquals(Error.NOT_DIAGONAL, draught.isCorrectMovement(pieces, 0, coordinatesCase2));
	}
	
	@Test
	public void testGivenDiagonalPiecesAndPairAndCordinateswhenisCorrectMovementThenErrorColleagueEating() {
		pieces.clear();
		pieces.add(draught);
		assertEquals(Error.COLLEAGUE_EATING, draught.isCorrectMovement(pieces, 0, coordinatesCase1));
	}
	
	@Test
	public void testGivenCoordinatewhenisLImitThenTrue() {
		assertTrue(draught.isLimit(coordinatesCase1[0]));
	}
	
	@Test
	public void testGivenCoordinatewhenisLImitThenFalse() {
		assertFalse(draught.isLimit(coordinatesCase1[1]));
	}
	

}
