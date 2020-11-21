package es.damas.views;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.controllers.InteractorController;
import es.damas.controllers.PlayController;
import es.damas.controllers.ResumeController;
import es.damas.controllers.StartController;
import es.damas.utils.Console;
import es.damas.utils.YesNoDialog;

@RunWith(MockitoJUnitRunner.class)
public class ViewTest {
	
	@Mock
	private InteractorController interactorController;
	
	@Mock
	private StartController startController;
	
	@Mock
	private PlayController playController;
	
	@Mock
	private PlayView playView;
    		
	@Mock
	private ResumeController resumeController;
	
	@Mock
	private Console console;

	@Mock
    private YesNoDialog yesNoDialog;
	
	@InjectMocks
    private View view = new View();
	
    private static final String TITTLE = "Draughts";
    
    private static final String MESSAGE = "¿Queréis jugar otra";
	
	@Test
    public void testGivenInteractorControllerWhenInteractThenIsCorrect() {
		doNothing().when(interactorController).accept(view);
		view.interact(interactorController);
		verify(interactorController).accept(view);
	}	
	
	@Test
    public void testGivenStartControllerWhenVisitThenIsCorrect() {
		doNothing().when(startController).start();
		doNothing().when(console).writeln(TITTLE);
		view.visit(startController);
		verify(console).writeln(TITTLE);
	}
	
	@Test
    public void testGivenPlayControllerWhenVisitThenIsCorrect() {
		doNothing().when(playView).interact(playController);
		view.visit(playController);
		verify(playView).interact(playController);

	}	
		
	@Test
    public void testGivenResumeControllerWhenVisitThenIsCorrect() {
		doNothing().when(resumeController).next();
		when(yesNoDialog.read(MESSAGE)).thenReturn(true);
		view.visit(resumeController);
		verify(resumeController).reset();
	}
	
	@Test
    public void testGivenPlayControllerWhenInteractThenIsNotCorrect() {
		doNothing().when(resumeController).reset();
		when(yesNoDialog.read(anyString())).thenReturn(false);
		view.visit(resumeController);
		verify(resumeController).next();
	}

}
