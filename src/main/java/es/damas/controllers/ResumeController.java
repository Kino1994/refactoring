package es.damas.controllers;

import es.damas.views.View;

public class ResumeController extends Controller {

	public void next() {
        state.next();
	}

	public void reset() {
		state.reset();
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
