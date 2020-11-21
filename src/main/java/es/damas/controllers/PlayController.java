package es.damas.controllers;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Error;
import es.damas.models.Game;
import es.damas.models.State;
import es.damas.views.View;

public class PlayController extends Controller {

	private static final int MINIMUM_COORDINATES = 2;


	public PlayController(Game game, State state) {
		super(game, state);
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
		this.game.cancel();
		this.state.next();
	}

	public Color getColor() {
		return this.game.getTurnColor();
	}

	public boolean isBlocked() {
		return this.game.isBlocked();
	}

	@Override
	public void control(View view) {
		view.visit(this);
	}

}