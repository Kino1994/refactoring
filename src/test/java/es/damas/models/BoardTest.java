package es.damas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class BoardTest {
	
	private Board board = new Board();
	
	private Coordinate coordinate1 = new Coordinate(1, 1);
	
	private Coordinate coordinate2 = new Coordinate(2, 2);	
	
	private Coordinate coordinate3 = new Coordinate(3, 3);	
	
	private Coordinate coordinateEnd = new Coordinate(8, 8);

	@Test
	public void testGivenCoordinateWhengetPieceThenNullIsCorrect() {
		assertEquals(null,board.getPiece(coordinate1));
	}
	
	@Test
	public void testGivenCoordinateWhengetPieceThenIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
		assertEquals(new Pawn(Color.BLACK),board.getPiece(coordinate1));
	}

	@Test
	public void testGivenCoordinateAndPieceWhenputThenIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
	}
	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGivenCoordinateAndPieceWhenputThenIsNotCorrect() {
		board.put(coordinateEnd, new Pawn(Color.BLACK));
	}


	@Test
	public void testGivenCoordinateWhenremoveThenIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
		board.remove(coordinate1);
		assertTrue(board.isEmpty(coordinate1));
	}

	@Test
	public void testGivenTwoCoordinatesWhenmoveTheIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
		board.move(coordinate1,coordinate2);
		assertTrue(board.isEmpty(coordinate1));
		assertFalse(board.isEmpty(coordinate2));
	}

	@Test
	public void testGivenTwoCoordinatesWhengetgetBetweenDiagonalPiecesThenIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
		board.put(coordinate2, new Pawn(Color.BLACK));
		board.put(coordinate3, new Pawn(Color.BLACK));
		List<Piece> pieces = board.getBetweenDiagonalPieces(coordinate1, coordinateEnd);
		for (Piece p: pieces) {
			assertEquals(Color.BLACK,p.getColor());
		}
	}

	@Test
	public void testGivenTwoCoordinatesWhengetAmountBetweenDiagonalPiecesThenIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
		board.put(coordinate2, new Pawn(Color.BLACK));
		board.put(coordinate3, new Pawn(Color.BLACK));
		assertEquals(1,board.getAmountBetweenDiagonalPieces(coordinate1, coordinate3));
	}

	@Test
	public void testGivenCoordinateWhengetgetColorThenBlackIsCorrect() {
		board.put(coordinate1, new Pawn(Color.BLACK));
		assertEquals(Color.BLACK,board.getColor(coordinate1));
	}
	
	@Test
	public void testGivenCoordinateWhengetgetColorThenWhiteIsCorrect() {
		board.put(coordinate2, new Pawn(Color.WHITE));
		assertEquals(Color.WHITE,board.getColor(coordinate2));
	}

	@Test
	public void testGivenCoordinateWhengetAmountBetwhenisEmptyThenIsCorrect(){
		assertTrue(board.isEmpty(coordinate1));
	}
	
	@Test
	public void testGivenCoordinateWhengetAmountBetwhenisEmptyThenIsNotCorrect(){
		board.put(coordinate1, new Pawn(Color.BLACK));
		assertFalse(board.isEmpty(coordinate1));
	}

}
