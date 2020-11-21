package es.damas.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Game;
import es.damas.models.State;
import es.damas.views.View;

@RunWith(MockitoJUnitRunner.class)
public class StartControllerTest {
	
	@Mock
	private Game game;
	
	@Mock
	private State state;
		
	@Mock
	private View view;
	
	private static final int DIMENSION = 7;
	
	@InjectMocks
	private StartController startController;
    
    @Test
   	public void testGivenGameAndStateWhenAcceptThenIsCorrect() {
    	when(game.getDimension()).thenReturn(DIMENSION);
    	doNothing().when(view).writeMenu(DIMENSION, game);
		startController.control(view);
    	verify(view).writeMenu(DIMENSION, game);
   	}
    

}