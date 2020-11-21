package es.damas.controllers;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.State;
import es.damas.models.Error;
import es.damas.models.Game;

public class PlayController extends InteractorController {

	private CancelController cancelController;

	private static final int MINIMUM_COORDINATES = 2;


	public PlayController(Game game, State state) {
		super(game, state);
		this.cancelController = new CancelController(game, state);
	}

	public Error move(Coordinate... coordinates) {
		assert coordinates.length >= MINIMUM_COORDINATES;
		for(Coordinate coordinate: coordinates)
			assert coordinate != null;
		Error error = this.game.move(coordinates);
		if (this.game.isBlocked())
			this.state.next();
		return error;
	}

	public void cancel() {
		this.cancelController.cancel();
	}

	public Color getColor() {
		return this.game.getTurnColor();
	}

	public boolean isBlocked() {
		return this.game.isBlocked();
	}

	@Override
	public void accept(InteractorControllersVisitor controllersVisitor) {
		assert controllersVisitor != null;
		controllersVisitor.visit(this);
	}

}