package es.damas.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Game;
import es.damas.views.View;

@RunWith(MockitoJUnitRunner.class)
public class ResumeControllerTest {

	@Mock
	private Game game;

	@Mock
	private View view;

	@InjectMocks
	private ResumeController resumeController;

	@Test
	public void giveGameAndStateWhenControlAndContinuePlayingThenNext() {
    	when(view.continuePlaying()).thenReturn(true);
		resumeController.control(view);
    	verify(view).continuePlaying();
    	verify(game).reset();
	}
	
	@Test
	public void giveGameAndStateWhenControlAndNoContinuePlayingThenReset() {
    	when(view.continuePlaying()).thenReturn(false);
		resumeController.control(view);
    	verify(view).continuePlaying();
	}

}
