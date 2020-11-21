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
	public void giveGameAndStateWhenAcceptThenIsCorrect() {
    	doNothing().when(view).visit(resumeController);
		resumeController.control(view);
    	verify(view).visit(resumeController);
	}

}
