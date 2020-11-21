package es.damas.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.controllers.Controller;
import es.damas.controllers.PlayController;
import es.damas.controllers.ResumeController;
import es.damas.controllers.StartController;
import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.Pawn;
import es.damas.utils.Console;
import es.damas.utils.YesNoDialog;

@RunWith(MockitoJUnitRunner.class)
public class ViewTest {
	
	@Mock
	private Controller controller;
	
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
	
	@Mock
	private Game game;
	
	@InjectMocks
    private View view = new View();
	
    private static final String TITTLE = "Draughts";
    
    private static final String MESSAGE = "¿Queréis jugar otra";
        
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
        
	private static final int DIMENSION = 7;
	
	private static final String BLACK = "n";
	
	private static final String WHITE = "b";
			
	private static final String EMPTY = " ";
	
    private static final String CANCEL_FORMAT = "-1";
		
    private static final String NO_CANCEL_FORMAT = "1";
    
    private static final String MOVEMENT_FORMAT = "11.22";

    private static final String NO_MOVEMENT_FORMAT = "11.";
            
	@Test
    public void testwhencontinuePlayingThenTrue() {
		when(yesNoDialog.read(MESSAGE)).thenReturn(true);
		assertTrue(view.continuePlaying());
		verify(yesNoDialog).read(MESSAGE);
	}
	
	@Test
    public void testwhencontinuePlayingThenFalse() {
		when(yesNoDialog.read(MESSAGE)).thenReturn(false);
		assertFalse(view.continuePlaying());
		verify(yesNoDialog).read(MESSAGE);
	}
	
	@Test
	public void testwhengetCoordinatesThenIsCorrect() {
		Coordinate [] coordinates = view.getCoordinates(MOVEMENT_FORMAT);
		for (int i = 0; i< coordinates.length; i++) {
			assertEquals(coordinates[i], new Coordinate(i, i));
		}
	}
	
	@Test
    public void testgivenDimensionAndGamewhenwriteMenuThenWriteBlackBoard() {
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.getPiece(any(Coordinate.class))).thenReturn(new Pawn(Color.BLACK));
		view.writeMenu(DIMENSION, game);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(2)).write(EMPTY);
		verify(console,times(49)).write(BLACK);
		verify(console,times(2)).writeln();
	}
	
	@Test
    public void testgivenDimensionAndGamewhenwriteMenuThenWriteWhiteBoard() {
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.getPiece(any(Coordinate.class))).thenReturn(new Pawn(Color.WHITE));
		view.writeMenu(DIMENSION, game);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(2)).write(EMPTY);
		verify(console,times(49)).write(WHITE);
		verify(console,times(2)).writeln();
	}
	
	
	@Test
    public void testgivenDimensionAndGamewhenwriteMenuThenWriteEmptyBoard() {
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.getPiece(any(Coordinate.class))).thenReturn(null);
		view.writeMenu(DIMENSION, game);
		for (int i = 1; i<=DIMENSION; i++) {
			verify(console,times(3)).write(String.valueOf(i));
		}
		verify(console,times(51)).write(EMPTY);
		verify(console,times(2)).writeln();
	}
	
	
	public void givenColorwhenreadColorThenBlack(){
	    assertEquals(BLACK,view.read(Color.BLACK));
	}
	
	public void givenColorwhenreadColorThenWhite(){
	    assertEquals(WHITE,view.read(Color.WHITE));
	}
	
	@Test
	public void testgivenInputwhenisCanceledFormatThenTrue() {
		assertTrue(view.isCanceledFormat(CANCEL_FORMAT));
	}
	
	@Test
	public void testgivenInputwhenisCanceledFormatThenFalse() {
		assertFalse(view.isCanceledFormat(NO_CANCEL_FORMAT));
	}

	@Test
	public void testwhenisMoveFormatThenTrue() {
		assertTrue(view.isMoveFormat(MOVEMENT_FORMAT));
	}
	
	@Test
	public void testwhenisMoveFormatThenFalse() {
		assertFalse(view.isMoveFormat(NO_MOVEMENT_FORMAT));
	}

	@Test
	public void testwhenwriteErrorThenisCorrect(){
		doNothing().when(console).writeln(ERROR_MESSAGE);
		view.writeError();
		verify(console).writeln(ERROR_MESSAGE);
	}

	@Test
	public void testwhenwriteLostThenisCorrect() {
		doNothing().when(console).writeln(LOST_MESSAGE);
		view.writeLost();
		verify(console).writeln(LOST_MESSAGE);
	}
	
	@Test
	public void testwhenwriteTittleThenisCorrect() {
		doNothing().when(console).writeln(TITTLE);
		view.writeTittle();
		verify(console).writeln(TITTLE);
	}	
	
}
