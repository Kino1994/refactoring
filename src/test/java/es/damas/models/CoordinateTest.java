package es.damas.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class CoordinateTest {
	
	private static Coordinate instance = Coordinate.getInstance("11");
	
	private Coordinate coordinate1 = new Coordinate(1, 1);
	
	private Coordinate coordinate2 = new Coordinate(3, 3);
	
	private Coordinate coordinate3 = new Coordinate(7, 7);

	
	@Test
	public void testGivenCoordinatewhengetInstanceThenIsCorrect() {	
		assertEquals(new Coordinate(0, 0),instance);
	}

	@Test
	public void testGivenCoordinatewhengetDirectionThenIsCorrect() {
		assertEquals(Direction.NE,instance.getDirection(coordinate1));
	}

	@Test
	public void testGivenCoordinatewhenisOnDiagonalThenIsCorrect() {
		assertTrue(instance.isOnDiagonal(coordinate1));
	}

	@Test
	public void testGivenCoordinatewhengetDiagonalDistanceThenIsCorrect() {
		assertEquals(1,instance.getDiagonalDistance(coordinate1));
	}

	@Test
	public void testGivenCoordinatewhengetBetweenDiagonalCoordinateThenIsCorrect() {
		assertEquals(new Coordinate(2, 2),coordinate1.getBetweenDiagonalCoordinate(coordinate2));
	}

	@Test
	public void testGivenCoordinatewhengetBetweenDiagonalCoordinatesThenIsCorrect() {
		List<Coordinate> coordinates = coordinate1.getBetweenDiagonalCoordinates(coordinate3);
		int i = coordinate1.getRow() + 1;
		for (Coordinate c: coordinates) {
			assertEquals(new Coordinate(i, i),c);
			i++;
		}
	}

	@Test
	public void testGivenCoordinatewhengetDiagonalCoordinatesThenIsCorrect() {
		List<Coordinate> coordinates = coordinate1.getDiagonalCoordinates(coordinate3.getRow());
		int i = coordinate3.getRow();
		for (Coordinate c: coordinates) {
			assertEquals(new Coordinate(i, i),c);
			i++;
		}	
	}

	@Test
	public void testWhenisBlackThenIsCorrect() {
		assertTrue(new Coordinate(2, 1).isBlack());
		assertTrue(new Coordinate(1, 2).isBlack());
	}
	
	@Test
	public void testWhenNotisBlackThenIsCorrect() {
		assertFalse(coordinate1.isBlack());
		assertFalse(coordinate2.isBlack());
		assertFalse(coordinate3.isBlack());
	}
	
	@Test
	public void testWhenisLastThenIsCorrect() {
		assertTrue(coordinate3.isLast());
	}

	@Test
	public void testWhenNotisLastThenIsCorrect() {
		assertFalse(coordinate1.isLast());
	}
	
	@Test
	public void testWhenisFirstThenIsCorrect() {
		assertTrue(instance.isFirst());
	}

	@Test
	public void testWhenNotisFirstThenIsCorrect() {
		assertFalse(coordinate1.isFirst());
	}

	@Test
	public void testWhengetColumnThenIsCorrect() {
		assertEquals(instance.getColumn(), 0);
	}

	@Test
	public void testWhengetDimensionThenIsCorrect() {
		assertEquals(coordinate1.getColumn(), 1);
	}

}
