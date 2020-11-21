package es.damas.controllers;

import es.damas.views.View;

public class StartController extends Controller {

	private void start() {
        state.next();
	}
    
	@Override
	public void control(View view) {
    	view.writeMenu(this.getDimension(), this.game);
        this.start();
	}
}
