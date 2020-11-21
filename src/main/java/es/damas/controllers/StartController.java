package es.damas.controllers;

import es.damas.models.Game;
import es.damas.models.State;
import es.damas.views.View;

public class StartController extends Controller {

	public StartController(Game game, State state) {
        super(game, state);
	}

	public void start() {
        this.state.next();
	}
    
	@Override
	public void control(View view) {
		view.visit(this);
	}

}
