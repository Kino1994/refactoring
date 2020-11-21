package es.damas.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Game;
import es.damas.models.State;

@RunWith(MockitoJUnitRunner.class)
public class CancelControllerTest {
	
	@Mock
	private Game game;
	
	@Mock
	private State state;
	
	@InjectMocks
    private CancelController cancelController;
    
    
    @Test
	public void testGivenGameAndStateWhencancelThenIsCorrect() {
		doNothing().when(game).cancel();		
		doNothing().when(state).next();
		cancelController.cancel();
		verify(game).cancel();		
		verify(state).next();	
	}

}
