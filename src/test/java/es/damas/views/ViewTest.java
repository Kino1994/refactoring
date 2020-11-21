package es.damas.views;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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
import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Pawn;
import es.damas.models.Piece;
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
	private ResumeController resumeController;
	
	@Mock
	private Console console;

	@Mock
    private YesNoDialog yesNoDialog;
	
	@InjectMocks
    private View view = new View();
	
    private static final String TITTLE = "Draughts";
    
    private static final String MESSAGE = "¿Queréis jugar otra";
    
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    
    private static final String BLACK_MOVE = "Mueven las negras: ";
    
    private static final String WHITE_MOVE = "Mueven las blancas: ";
    
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    
	private static final int DIMENSION = 7;
	
	private static final String BLACK = "n";
	
	private static final String WHITE = "b";
			
	private static final String EMPTY = " ";			
	
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
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString(BLACK_MOVE)).thenReturn("12.22");
		when(playController.move(any(Coordinate.class))).thenReturn(null);
		when(playController.isBlocked()).thenReturn(true);
		view.visit(playController);
		verify(console).writeln(LOST_MESSAGE);
	}
	
	@Test
    public void testGivenPlayControllerWhenVisitAndInteractMovementFormatIsCancelhenIsCorrect() {
		when(playController.getColor()).thenReturn(Color.WHITE);
		when(console.readString(WHITE_MOVE)).thenReturn("-1");
		doNothing().when(playController).cancel();
		view.visit(playController);
		verify(playController).cancel();
	}	
	
	@Test
	public void testgivenPlayControllerWhenVisitAndInteractMovementFormatIsNotCorrectThenIsCorrect() {
		when(playController.getColor()).thenReturn(Color.BLACK);
		when(console.readString(BLACK_MOVE)).thenReturn(ERROR_MESSAGE).thenReturn("12.22");
		when(playController.move(any(Coordinate.class))).thenReturn(null);
		when(playController.isBlocked()).thenReturn(true);
		view.visit(playController);
		verify(console).writeln(LOST_MESSAGE);
	}
		
	@Test
    public void testGivenResumeControllerWhenVisitThenIsCorrect() {
		doNothing().when(resumeController).next();
		when(yesNoDialog.read(MESSAGE)).thenReturn(true);
		view.visit(resumeController);
		verify(resumeController).reset();
	}
	
	@Test
    public void testGivenResumeControllerWhenVisitThenIsNotCorrect() {
		doNothing().when(resumeController).reset();
		when(yesNoDialog.read(anyString())).thenReturn(false);
		view.visit(resumeController);
		verify(resumeController).next();
	}
	
	@Test
    public void testGivenInteractorControllerWhenWriteThenViewAllBlacks() {
		when(interactorController.getDimension()).thenReturn(DIMENSION);
		when(interactorController.getPiece(any(Coordinate.class))).thenReturn(new Pawn(Color.BLACK));
		view.write(interactorController);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(2)).write(EMPTY);
		verify(console,times(49)).write(BLACK);
		verify(console,times(2)).writeln();
	}
	
	@Test
    public void testGivenInteractorControllerWhenWriteThenViewAllWhites() {
		when(interactorController.getDimension()).thenReturn(DIMENSION);
		when(interactorController.getPiece(any(Coordinate.class))).thenReturn(new Pawn(Color.WHITE));
		view.write(interactorController);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(2)).write(EMPTY);
		verify(console,times(49)).write(WHITE);
		verify(console,times(2)).writeln();
	}
	
	
	@Test
    public void testGivenInteractorControllerWhenWriteThenViewAllEmpty() {
		when(interactorController.getDimension()).thenReturn(DIMENSION);
		when(interactorController.getPiece(any(Coordinate.class))).thenReturn(null);
		view.write(interactorController);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(51)).write(EMPTY);
		verify(console,times(2)).writeln();
	}
	
    

	
}
