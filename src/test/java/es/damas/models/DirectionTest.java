package es.damas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DirectionTest {
	
	private Direction direction = Direction.NE;
	
	@Test
	public void testWhenisOnDirectionThenTrue(){
		assertTrue(direction.isOnDirection(new Coordinate(1, 1)));
	}
	
	@Test
	public void testWhenisOnDirectionThenFalse(){
		assertFalse(direction.isOnDirection(new Coordinate(0, 1)));
		assertFalse(direction.isOnDirection(new Coordinate(1, 0)));
		assertFalse(direction.isOnDirection(new Coordinate(0, 0)));
		assertFalse(direction.isOnDirection(new Coordinate(2, 1)));
		assertFalse(direction.isOnDirection(new Coordinate(1, 2)));
	}
	
	@Test
	public void testWhenisgetDistanceCoordinateThenIsCorrect(){
		Coordinate coordinate = direction.getDistanceCoordinate(1);
		assertEquals(coordinate.getRow(), 1);
		assertEquals(coordinate.getColumn(), 1);
	}
	
	
	

}
