package es.damas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {
	
	Board board = new Board();
	
	private Game game = new Game(board);
	
	private static final int DIMENSION = 8;
	
	@Test
	public void whenresetThenIsCorrect() {
		assertEquals(Color.WHITE, game.getTurnColor());			
		game.reset();
		assertEquals(Color.WHITE, game.getTurnColor());			
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		game.move(new Coordinate(1,1), new Coordinate(0,0));
		assertEquals(Color.BLACK, game.getTurnColor());			
		board.put(new Coordinate(2,2), new Pawn(Color.BLACK));
		game.move(new Coordinate(2,2), new Coordinate(3,3));
		assertEquals(Color.WHITE, game.getTurnColor());			
		game.reset();
		assertEquals(Color.WHITE, game.getTurnColor());			
	}

	@Test
	public void givenCoordinatewhenmoveThenIsCorrect() {
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		game.move(new Coordinate(1,1), new Coordinate(0,0));
		assertEquals(new Draught(Color.WHITE), game.getPiece(new Coordinate(0,0)));		
		board.put(new Coordinate(2,2), new Pawn(Color.BLACK));
		game.move(new Coordinate(2,2), new Coordinate(3,3));
		assertEquals(new Pawn(Color.BLACK), game.getPiece(new Coordinate(3,3)));		
	}
	
	@Test
	public void whenisBlockedThenIsCorrect() {
		board.put(new Coordinate(0,0), new Pawn(Color.BLACK));
		board.put(new Coordinate(0,2), new Pawn(Color.BLACK));
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		assertTrue(game.isBlocked());	
		
	}
	
	@Test
	public void whenisNotBlockedThenIsCorrect() {
		board.put(new Coordinate(0,0), new Pawn(Color.BLACK));
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		assertFalse(game.isBlocked());		
	}	

	@Test
	public void whencancelThenIsCorrect() {
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		game.move(new Coordinate(1,1), new Coordinate(0,0));
		board.put(new Coordinate(2,2), new Pawn(Color.BLACK));
		game.move(new Coordinate(2,2), new Coordinate(3,3));
		assertEquals(new Draught(Color.WHITE), game.getPiece(new Coordinate(0,0)));	
		assertEquals(new Pawn(Color.BLACK), game.getPiece(new Coordinate(3,3)));	
		game.cancel();
		assertEquals(null, game.getPiece(new Coordinate(0,0)));	
	}

	@Test
	public void givenCoordinatewhengetColorThenIsCorrect() {
		board.put(new Coordinate(0,0), new Pawn(Color.BLACK));
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		assertEquals(Color.BLACK,game.getColor(new Coordinate(0,0)));		
		assertEquals(Color.WHITE,game.getColor(new Coordinate(1,1)));
	}

	@Test
	public void whengetTurnColorThenIsCorrectThenIsCorrect() {
		assertEquals(Color.WHITE, game.getTurnColor());
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		game.move(new Coordinate(1,1), new Coordinate(0,0));
		assertEquals(Color.BLACK, game.getTurnColor());
		board.put(new Coordinate(2,2), new Pawn(Color.BLACK));
		game.move(new Coordinate(2,2), new Coordinate(3,3));
		assertEquals(Color.WHITE, game.getTurnColor());
	}

	@Test
	public void givenCoordinatewhengetPieceThenIsCorrect() {
		assertEquals(Color.WHITE, game.getTurnColor());
		
		board.put(new Coordinate(1,1), new Pawn(Color.WHITE));
		assertEquals(new Pawn(Color.WHITE), game.getPiece(new Coordinate(1,1)));
		assertEquals(null, game.getPiece(new Coordinate(0,0)));
		
		game.move(new Coordinate(1,1), new Coordinate(0,0));
		assertEquals(new Draught(Color.WHITE), game.getPiece(new Coordinate(0,0)));
		assertEquals(null, game.getPiece(new Coordinate(1,1)));
		assertEquals(Color.BLACK, game.getTurnColor());
		
		board.put(new Coordinate(2,2), new Pawn(Color.BLACK));
		assertEquals(new Pawn(Color.BLACK), game.getPiece(new Coordinate(2,2)));
		assertEquals(null, game.getPiece(new Coordinate(3,3)));
		
		game.move(new Coordinate(2,2), new Coordinate(3,3));
		assertEquals(null, game.getPiece(new Coordinate(2,2)));
		assertEquals(new Pawn(Color.BLACK), game.getPiece(new Coordinate(3,3)));
		assertEquals(Color.WHITE, game.getTurnColor());
	}

	@Test
	public void whengetDimensionThenIsCorrect() {
		board.put(new Coordinate(5,6), new Pawn(Color.BLACK));
		assertEquals(DIMENSION,game.getDimension());
	}

}
