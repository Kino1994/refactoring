package usantatecla.tictactoe.views.console;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.types.Error;
import usantatecla.utils.Console;

@ExtendWith(MockitoExtension.class)
public class CoordinateViewTest {

    @Mock
    private Console console;

    @Mock
    private PlayController playController;

    @InjectMocks
    private CoordinateView coordinateView;

    @Test
    void testGivenNewCoordinateViewWhenReadCoordinateThenReturnCoordinate() {
        try (MockedStatic console = mockStatic(Console.class)) {
            when(this.console.readInt(anyString())).thenReturn(1);
            console.when(Console::getInstance).thenReturn(this.console);
            Coordinate coordinate = this.coordinateView.read("");
            verify(this.console).writeln("");
            assertThat(coordinate, is(new Coordinate(0, 0)));
        }
    }

    @Test
    void testGivenNewCoordinateViewWhenReadInvalidCoordinateThenReadValidCoordinateAndReturnValidCoordinate() {
        try (MockedStatic console = mockStatic(Console.class)) {
            when(this.console.readInt(anyString())).thenReturn(4, 1);
            console.when(Console::getInstance).thenReturn(this.console);
            Coordinate coordinate = this.coordinateView.read("");
            verify(this.console, times(2)).writeln("");
            verify(this.console, times(4)).readInt(anyString());
            assertThat(coordinate, is(new Coordinate(0, 0)));
        }
    }

}
