package usantatecla.tictactoe.views.console;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import usantatecla.tictactoe.controllers.PlayController;
import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.types.Error;
import usantatecla.tictactoe.types.Token;
import usantatecla.utils.Console;

@ExtendWith(MockitoExtension.class)
public class PlayViewTest {

    @Mock
    private PlayController playController;

    @InjectMocks
    private PlayView playView;

    @Mock
    private Console console;
    
    @Mock
    Token token;

    @Test
    void testGivenNewPlayViewWhenUserPlayerPutCoordinateThenGamePutCoordinate() {
        try (MockedStatic console = mockStatic(Console.class)) {
            when(this.playController.isBoardComplete()).thenReturn(false);
            when(this.playController.isUser()).thenReturn(true);
            when(this.console.readInt(anyString())).thenReturn(1);
            when(this.playController.put(any(Coordinate.class))).thenReturn(Error.NULL);
            when(this.playController.getToken(any(Coordinate.class))).thenReturn(token);
            console.when(Console::getInstance).thenReturn(this.console);
            this.playView.interact(this.playController);
        }
    }

    @Test
    void testGivenNewPlayViewWhenMachinePlayerPutCoordinateThenGamePutCoordinate() {
        try (MockedStatic console = mockStatic(Console.class)) {
            Coordinate coordinate = new Coordinate(0,0);
            when(this.playController.isBoardComplete()).thenReturn(false);
            when(this.playController.isUser()).thenReturn(false);
            when(this.playController.put(any(Coordinate.class))).thenReturn(Error.NULL);
            when(this.playController.getToken(any(Coordinate.class))).thenReturn(token);
            console.when(Console::getInstance).thenReturn(this.console);
            this.playView.interact(this.playController);
        }
    }

    @Test
    void testGivenNewPlayViewWhenUserPlayerMoveOriginToTargetThenGameMoveOriginToTarget() {
        try (MockedStatic console = mockStatic(Console.class)) {
            when(this.playController.isBoardComplete()).thenReturn(true);
            when(this.playController.isUser()).thenReturn(true);
            when(this.console.readInt(anyString())).thenReturn(1, 1, 2, 2);
            when(this.playController.move(any(Coordinate.class), any(Coordinate.class))).thenReturn(Error.NULL);
            when(this.playController.getToken(any(Coordinate.class))).thenReturn(token);
            console.when(Console::getInstance).thenReturn(this.console);
            this.playView.interact(this.playController);
        }
    }

    @Test
    void testGivenNewPlayViewWhenMachinePlayerMoveOriginToTargetThenGameMoveOriginToTarget() {
        try (MockedStatic console = mockStatic(Console.class)) {
            when(this.playController.isBoardComplete()).thenReturn(true);
            when(this.playController.isUser()).thenReturn(false);
            when(this.playController.getToken(any(Coordinate.class))).thenReturn(token);
            when(this.playController.move(any(Coordinate.class), any(Coordinate.class))).thenReturn(Error.NULL);
            console.when(Console::getInstance).thenReturn(this.console);
            this.playView.interact(this.playController);
        }
    }

}
