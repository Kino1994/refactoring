package es.damas.controllers;

import es.damas.models.Game;
import es.damas.models.State;
import es.damas.views.View;

public class ResumeController extends Controller {

	public ResumeController(Game game, State state) {
        super(game, state);
	}

	public void next() {
        this.state.next();
	}

	public void reset() {
		this.state.reset();
		this.game.reset();
	}

	@Override
	public void control(View view) {
        if (view.continuePlaying())
            this.reset();
        else
            this.next();
	}

}
