package es.damas.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TurnTest {
	
	private Turn turn = new Turn();
	
	@Test
	public void testWhenchangeThenBlack() {
		assertEquals(turn.getColor(), Color.WHITE);
		turn.change();
		assertEquals(turn.getColor(), Color.BLACK);
		turn.change();
		assertEquals(turn.getColor(), Color.WHITE);
	}

	@Test
	public void testWhengetColorThenWhite() {
		assertEquals(turn.getColor(), Color.WHITE);
	}
}
