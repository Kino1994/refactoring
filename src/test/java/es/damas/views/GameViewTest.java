package es.damas.views;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.controllers.InteractorController;
import es.damas.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class GameViewTest {
	
	@Mock
	private InteractorController interactorController;	

	@Mock
	private Console console;
	
	@InjectMocks
    private GameView gameView = new GameView();
	
	private static final int DIMENSION = 7;
	
	@Test
    public void testGivenInteractorControllerWhenWriteThenViewIsCorrect() {
		when(interactorController.getDimension()).thenReturn(DIMENSION);
		gameView.write(interactorController);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(51)).write(" ");
		verify(console,times(2)).writeln();
	}
	

}
