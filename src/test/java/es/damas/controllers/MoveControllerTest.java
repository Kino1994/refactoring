package es.damas.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Coordinate;
import es.damas.models.Error;
import es.damas.models.Game;
import es.damas.models.State;

@RunWith(MockitoJUnitRunner.class)
public class MoveControllerTest {

	@Mock
	private Game game;

	@Mock
	private State state;

	@InjectMocks
	private MoveController moveController;

	private Coordinate[] coordinates = { new Coordinate(1, 1), new Coordinate(2, 2) };
	
	@Test
	public void testGivenGameAndStateWhenMoveThenIsCorrect() {
		when(game.move(coordinates)).thenReturn(Error.BAD_FORMAT);
		assertEquals(Error.BAD_FORMAT, moveController.move(coordinates));
	}

}
