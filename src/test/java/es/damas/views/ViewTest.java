package es.damas.views;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

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
    private ResumeView resumeView;
		
	@Mock
	private ResumeController resumeController;
	
	@Mock
	private Console console;
	
	@InjectMocks
    private View view = new View();
	
    private static final String TITTLE = "Draughts";
	
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
		doNothing().when(resumeView).interact(resumeController);
		view.visit(resumeController);
		verify(resumeView).interact(resumeController);

	}	

}
