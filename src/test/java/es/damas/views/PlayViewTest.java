package es.damas.views;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.controllers.PlayController;
import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.utils.Console;

@RunWith(MockitoJUnitRunner.class)
public class PlayViewTest {
	
	@Mock
	private PlayController playController;
	
	@Mock
	private Console console;
	
	@InjectMocks
    private PlayView playView = new PlayView();
	
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    
    private static final String BLACK_MOVE = "Mueven las negras: ";
    
    private static final String WHITE_MOVE = "Mueven las blancas: ";
    
    // private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";

	@Test
	public void testGivenPlayControllerWhenInteractThenIsCorrect() {
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString(BLACK_MOVE)).thenReturn("12.22");
		when(playController.move(any(Coordinate.class))).thenReturn(null);
		when(playController.isBlocked()).thenReturn(true);
		playView.interact(playController);		
		verify(console).writeln(LOST_MESSAGE);
	}
	
	@Test
	public void testGivenPlayControllerWhenInteractMovementFormatIsCancelhenIsCorrect() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(console.readString(WHITE_MOVE)).thenReturn("-1");
		doNothing().when(playController).cancel();
		playView.interact(playController);		
		verify(playController).cancel();
	}
	
	/*@Test(timeout=1000) // Bucle infinito debido a error de formato ya que si lo introduce mal el usuario se lo pide de nuevo
	public void testgivenPlayControllerWhenInteractMovementFormatIsNotCorrectThenIsNotCorrect() {
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString(BLACK_MOVE)).thenReturn("ERROR");
		playView.interact(playController);		
		verify(console).writeln(ERROR_MESSAGE);
	}*/
	
}
