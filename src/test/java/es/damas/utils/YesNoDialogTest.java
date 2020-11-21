package es.damas.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class YesNoDialogTest {

	@Mock
	private Console console;
	
	@InjectMocks
    private YesNoDialog yesNoDialog; 
	
    
	@Test
	public void testGivenNewYesNoDialogWhenReadAfirmativeThenIsCorrect() {
		when(console.readChar("1? (y/n): ")).thenReturn('y');
        assertEquals(true, this.yesNoDialog.read("1"));		
	}
	
	@Test
	public void testGivenNewYesNoDialogWhenReadNegativeThenIsCorrect() {
		when(console.readChar("1? (y/n): ")).thenReturn('n');
        assertEquals(false, this.yesNoDialog.read("1"));		
	}

}
