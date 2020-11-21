package es.damas.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.Pawn;
import es.damas.models.State;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	@Mock
	private Game game;

	@Mock
	private State state;

	private Controller controller;
	
	private static final int DIMENSION = 8;
	
	private Coordinate coordinate1 = new Coordinate(2, 1);
	
	private Coordinate coordinate2 = new Coordinate(6, 1);

	@Before
	public void setUp() {
		controller = new PlayController();	
	}
		
	@Test
	public void testGivenGameAndStateWhengetColorThenIsBlack() {
		when(game.getColor(coordinate1)).thenReturn(Color.BLACK);
    	assertEquals(Color.BLACK, controller.getColor(coordinate1));	
	}
	
	@Test
	public void testGivenGameAndStateWhengetColorThenIsWhite() {
		when(game.getColor(coordinate2)).thenReturn(Color.WHITE);
    	assertEquals(Color.WHITE, controller.getColor(coordinate2));	
	}
	
	@Test
	public void testGivenGameAndStateWhengetDimensionThenIsCorrect() {
		when(game.getDimension()).thenReturn(7);
    	assertEquals(DIMENSION, controller.getDimension());	
	}
	
	@Test
	public void testGivenGameAndStateWhengetPieceThenIsCorrect() {
		when(game.getPiece(coordinate1)).thenReturn(new Pawn(Color.BLACK));
    	assertTrue(controller.getPiece(coordinate1) instanceof Pawn);	
	}

}
