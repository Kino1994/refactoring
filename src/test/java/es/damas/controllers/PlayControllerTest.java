package es.damas.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
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
import es.damas.views.View;

@RunWith(MockitoJUnitRunner.class)
public class PlayControllerTest {
	
	@Mock
	private Game game;
	
	@Mock
	private Piece piece;
	
	@Mock
	private View view;
			
	@InjectMocks
    private PlayController playController;
	
    private static final String MOVEMENT_FORMAT = "11.22";
    
    private static final int DIMENSION = 7;
   
	
	private Coordinate [] coordinates = {new Coordinate(1, 1),new Coordinate(2, 2)};
    
    @Test
   	public void testGivenGameAndStateWhenMoveThenIsCorrect() {
    	when(game.move(coordinates)).thenReturn(Error.BAD_FORMAT);
    	assertEquals(Error.BAD_FORMAT, playController.move(coordinates));	
   	}
    
    @Test
   	public void testGivenGameAndStateWhenCancelThenIsCorrect() {
    	doNothing().when(game).cancel();		
		playController.cancel();
		verify(game).cancel();		
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
   	public void giveGameAndStateWhenControlThenMove() {
    	when(game.getTurnColor()).thenReturn(Color.WHITE);
    	when(view.read(Color.WHITE)).thenReturn(MOVEMENT_FORMAT); 
    	when(view.isCanceledFormat(MOVEMENT_FORMAT)).thenReturn(false);
    	when(view.isMoveFormat(MOVEMENT_FORMAT)).thenReturn(true);
    	when(view.getCoordinates(MOVEMENT_FORMAT)).thenReturn(coordinates);
		when(game.move(eq(coordinates))).thenReturn(null);
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.isBlocked()).thenReturn(false);
     	playController.control(view);
        verify(view).writeMenu(DIMENSION, this.game);
   	}
    
    @Test
   	public void giveGameAndStateWhenControlThenBadMoveFormatAndMove() {    	
    	when(game.getTurnColor()).thenReturn(Color.WHITE);
    	when(view.read(Color.WHITE)).thenReturn(MOVEMENT_FORMAT); 
    	when(view.isCanceledFormat(MOVEMENT_FORMAT)).thenReturn(false);
    	doNothing().when(view).writeError();
    	when(view.isMoveFormat(MOVEMENT_FORMAT)).thenReturn(false).thenReturn(true);
    	when(view.getCoordinates(MOVEMENT_FORMAT)).thenReturn(coordinates);
		when(game.move(eq(coordinates))).thenReturn(Error.NOT_DIAGONAL).thenReturn(null);
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.isBlocked()).thenReturn(false);
     	playController.control(view);
    	verify(view).writeError();
        verify(view).writeMenu(DIMENSION, this.game);
   	}
    
    @Test
   	public void giveGameAndStateWhenControlThenCancel() {    	
    	when(game.getTurnColor()).thenReturn(Color.WHITE);
    	when(view.read(Color.WHITE)).thenReturn(MOVEMENT_FORMAT); 
    	when(view.isCanceledFormat(MOVEMENT_FORMAT)).thenReturn(true).thenReturn(false);
    	doNothing().when(game).cancel();
    	when(view.isMoveFormat(MOVEMENT_FORMAT)).thenReturn(true);
    	when(view.getCoordinates(MOVEMENT_FORMAT)).thenReturn(coordinates);
		when(game.move(eq(coordinates))).thenReturn(Error.NOT_DIAGONAL).thenReturn(null);
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.isBlocked()).thenReturn(false);
     	playController.control(view);
    	verify(game).cancel();
   	}
    
    @Test
   	public void giveGameAndStateWhenControlThenErrorAndMove() {
    	when(game.getTurnColor()).thenReturn(Color.WHITE);
    	when(view.read(Color.WHITE)).thenReturn(MOVEMENT_FORMAT); 
    	when(view.isCanceledFormat(MOVEMENT_FORMAT)).thenReturn(false);
    	when(view.isMoveFormat(MOVEMENT_FORMAT)).thenReturn(true);
    	when(view.getCoordinates(MOVEMENT_FORMAT)).thenReturn(coordinates);
		when(game.move(eq(coordinates))).thenReturn(Error.NOT_DIAGONAL).thenReturn(null);
		when(game.getDimension()).thenReturn(DIMENSION);
		when(game.isBlocked()).thenReturn(false);
     	playController.control(view);
        verify(view).writeMenu(DIMENSION, this.game);
   	}
    
}
