
package usantatecla.utils;

import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class ConsoleTest {

    @Mock
    private BufferedReader bufferedReader;

    @InjectMocks
    private Console console = new Console();

    @Test
    public void testGivenNewConsoleWhenReadLineCorrectStringThenIsCorrect() throws IOException {
        when(this.bufferedReader.readLine()).thenReturn("1");
        assertThat("1", is(this.console.readString("")));
    }

    @Test
    public void testGivenNewConsoleWhenReadLineCorrectStringIntegerThenIsCorrect() throws IOException {
        when(this.bufferedReader.readLine()).thenReturn("1");
        assertThat(1, is(this.console.readInt("")));
    }

    @Test
    public void testGivenNewConsoleWhenReadLineCorrectStringIntegerThenIsInCorrect() throws IOException {
        when(this.bufferedReader.readLine()).thenReturn("a");
        assertThrows(AssertionError.class, ()-> {
        	this.console.readInt("");
        });
    }

    @Test
    public void testGivenNewConsoleWhenReadLineCorrectStringCharThenIsCorrect() throws IOException {
        when(this.bufferedReader.readLine()).thenReturn("a");
        assertThat('a', is(this.console.readChar("")));
    }

    @Test
    public void testGivenNewConsoleWhenReadLineCorrectStringCharThenIsInCorrect() throws IOException {
        when(this.bufferedReader.readLine()).thenReturn("a");
        assertThat(1, is(not(this.console.readChar(""))));
    }

} 
