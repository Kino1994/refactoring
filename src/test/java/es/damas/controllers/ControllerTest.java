package es.damas.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.State;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	@Mock
	private Game game;

	@Mock
	private State state;

	@InjectMocks
	private Controller controller;

	private Coordinate coordinate = new Coordinate(1, 1);
		
	@Test
	public void testGivenGameAndStateWhengetColorThenIsCorrect() {
		when(game.getColor(coordinate)).thenReturn(Color.BLACK);
    	assertEquals(Color.BLACK, controller.getColor(coordinate));	
	}
	
	@Test
	public void testGivenGameAndStateWhengetDimensionThenIsCorrect() {
		when(game.getDimension()).thenReturn(7);
    	assertEquals(7, controller.getDimension());	
	}

}
