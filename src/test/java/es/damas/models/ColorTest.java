package es.damas.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ColorTest {
	
	@Test
	public void testWhengetInitialColorTheIsCorrect() {
		assertEquals(Color.BLACK, Color.getInitialColor(new Coordinate(1, 0)));
	}
	
	@Test
	public void testWhengetInitialColorTheIsNotCorrect() {
		assertEquals(null, Color.getInitialColor(new Coordinate(0, 0)));
	}

}
