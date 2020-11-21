package es.damas.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Game;
import es.damas.models.State;
import es.damas.models.StateValue;

@RunWith(MockitoJUnitRunner.class)
public class LogicTest {
	
	@Mock
	private Game game;
	
	@Mock
	private State state;
	
	@Mock
	private Map<StateValue, InteractorController> controllers;
	
	@Mock
	private PlayController playController;
	
	@InjectMocks
    private Logic logic;
	
	@Test
	public void testWhengetControllerThenIsCorrect() {
		when(state.getValueState()).thenReturn(StateValue.IN_GAME);
		when(controllers.get(StateValue.IN_GAME)).thenReturn(playController);
		assertEquals(playController, logic.getController());		
	}

}
