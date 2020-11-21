package es.damas.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Error;
import es.damas.models.Game;
import es.damas.models.Piece;
import es.damas.models.State;

@RunWith(MockitoJUnitRunner.class)
public class PlayControllerTest {
	
	@Mock
	private Game game;
	
	@Mock
	private State state;
	
	@Mock
	private Piece piece;
	
	@Mock
	private CancelController cancelController;
	
	@Mock
	private InteractorControllersVisitor interactorControllersVisitor;
	
	@InjectMocks
    private PlayController playController;
	
	private Coordinate [] coordinates = {new Coordinate(1, 1),new Coordinate(2, 2)};
    
    @Test
   	public void testGivenGameAndStateWhenMoveThenIsCorrect() {
    	when(game.move(coordinates)).thenReturn(Error.BAD_FORMAT);
    	assertEquals(Error.BAD_FORMAT, playController.move(coordinates));	
   	}
    
    @Test
   	public void testGivenGameAndStateWhenCancelThenIsCorrect() {
   		doNothing().when(cancelController).cancel();
   		playController.cancel();
   		verify(game).cancel();		
   		verify(state).next();	
   	}
    
    @Test
   	public void giveGameAndStateWhenGetColorThenIsCorrect() {
   		when(game.getTurnColor()).thenReturn(Color.BLACK);
    	assertEquals(Color.BLACK, playController.getColor());		
   	}
    
    @Test
   	public void giveGameAndStateWhenIsBlockedThenIsCorrect() {
    	when(game.isBlocked()).thenReturn(true);
    	assertEquals(true, playController.isBlocked());	
   	}
    
    @Test
   	public void giveGameAndStateWhenAcceptThenIsCorrect() {
    	doNothing().when(interactorControllersVisitor).visit(playController);
    	playController.accept(interactorControllersVisitor);
    	verify(interactorControllersVisitor).visit(playController);
   	}
    
}
