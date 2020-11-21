package es.damas.controllers;

import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.Piece;
import es.damas.models.State;

public abstract class InteractorController extends Controller {

	protected InteractorController(Game game, State state) {
		super(game, state);
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}

	abstract public void accept(InteractorControllersVisitor controllersVisitor);

}
