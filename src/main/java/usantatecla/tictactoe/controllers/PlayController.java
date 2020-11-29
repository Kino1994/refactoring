package usantatecla.tictactoe.controllers;

import java.util.Random;

import usantatecla.tictactoe.models.Coordinate;
import usantatecla.tictactoe.models.Game;
import usantatecla.tictactoe.models.Session;
import usantatecla.tictactoe.types.Error;
import usantatecla.tictactoe.types.Token;
import usantatecla.utils.ClosedInterval;

public class PlayController extends Controller {
	
	private Session session = new Session();
	private UndoController undoController;
	private RedoController redoController;

	public PlayController(Game game) {
		super(game);
	}

	public boolean isBoardComplete() {
		return this.game.isBoardComplete();
	}

	public boolean isTicTacToe() {
		return this.game.isTicTacToe();
	}

	public Token getToken() {
		return this.game.getToken();
	}

	public boolean isUser() {
		return this.game.isUser();
	}

	public Error isValidCoordinate(int[] coordinate) {
		ClosedInterval limits = new ClosedInterval(0, Coordinate.DIMENSION - 1);
		if (!limits.isIncluded(coordinate[0]) || !limits.isIncluded(coordinate[1])) {
			return Error.NOT_VALID;
		}
		return Error.NULL;
	}

	public Coordinate getRandomCoordinate() {
		Random random = new Random(System.currentTimeMillis());
		int row = random.nextInt(Coordinate.DIMENSION);
		int column = random.nextInt(Coordinate.DIMENSION);
		return new Coordinate(row,column);
	}

	public Error put(int[] coordinate) {
		return this.game.put(new Coordinate(coordinate[0], coordinate[1]));
	}

	public Error move(int[] origin, int[] target) {
		return this.game.move(new Coordinate(origin[0], origin[1]), new Coordinate(target[0], target[1]));
	}

	public Error put(Coordinate coordinate) {
		Error error = this.session.put(coordinate);
		if (error.isNull() && this.session.isTicTacToe()) {
			this.session.next();
		}
		return error;
	}

	public Error move(Coordinate origin, Coordinate target) {
		Error error = this.session.move(origin, target);
		if (error.isNull() && this.session.isTicTacToe()) {
			this.session.next();
		}
		return error;
	}
	
	public void undo() {
		this.undoController.undo();
	}

	public boolean undoable() {
		return this.undoController.undoable();
	}

	public void redo() {
		this.redoController.redo();
	}

	public boolean redoable() {
		return this.redoController.redoable();
	}

}
