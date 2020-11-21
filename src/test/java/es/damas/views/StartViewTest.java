package es.damas.views;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.controllers.StartController;
import es.damas.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class StartViewTest {
	
	@Mock
	private StartController startController;
	
	@Mock
	private Console console;
	
	@InjectMocks
    private StartView startView = new StartView();
	
    private static final String TITTLE = "Draughts";
	
	@Test
    public void testGivenStartControllerWhenInteractThenIsCorrect() {
		doNothing().when(startController).start();
		when(startController.getDimension()).thenReturn(7);
		startView.interact(startController);
		verify(console).writeln(TITTLE);
	}	

}
