package es.damas.controllers;

import es.damas.models.Game;
import es.damas.models.State;
import es.damas.views.View;

public class StartController extends Controller {

	public StartController(Game game, State state) {
        super(game, state);
	}

	private void start() {
        this.state.next();
	}
    
	@Override
	public void control(View view) {
    	view.writeMenu(this.getDimension(), this.game);
        this.start();
	}
}
