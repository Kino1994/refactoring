package es.damas.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StateTest {
	
	private State state = new State();
	
	@Test
	public void testWhenNextThenIsCorrect() {
		assertEquals(state.getValueState(), StateValue.INITIAL);
	}
	
	@Test
	public void testWhenNextNextThenIsCorrect() {
		state.next();
		assertEquals(state.getValueState(), StateValue.IN_GAME);
	}
	
	@Test
	public void testWhenNextNextNextThenIsCorrect() {
		state.next();
		state.next();
		assertEquals(state.getValueState(), StateValue.FINAL);
	}
	
	@Test()
	public void testWhenNextNextNextNextThenIsCorrect() {
		state.next();
		state.next();
		state.next();
		assertEquals(state.getValueState(), StateValue.EXIT);
	}
	
	@Test(expected = AssertionError.class)
	public void testWhenNextNextNextNextThenIsNotCorrect() {
		state.next();
		state.next();
		state.next();
		state.next();
		state.getValueState();
	}
	
	@Test
	public void testWhenResetThenIsCorrect() {
		state.reset();
		assertEquals(state.getValueState(), StateValue.INITIAL);
	}

	@Test
	public void testWhengetValueStateThenIsCorrect() {
		state.reset();
		assertEquals(state.getValueState(), StateValue.INITIAL);
	}
}
