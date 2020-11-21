package es.damas.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Game;
import es.damas.models.State;
import es.damas.views.View;

@RunWith(MockitoJUnitRunner.class)
public class ResumeControllerTest {

	@Mock
	private Game game;

	@Mock
	private State state;

	@Mock
	private View view;

	@InjectMocks
	private ResumeController resumeController;

	@Test
	public void giveGameAndStateWhenControlAndContinuePlayingThenNext() {
    	when(view.continuePlaying()).thenReturn(true);
		resumeController.control(view);
    	verify(view).continuePlaying();
    	verify(state).reset();
    	verify(game).reset();
	}
	
	@Test
	public void giveGameAndStateWhenControlAndNoContinuePlayingThenReset() {
    	when(view.continuePlaying()).thenReturn(false);
		resumeController.control(view);
    	verify(view).continuePlaying();
    	verify(state).next();
	}

}
