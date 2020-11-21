package es.damas;

import java.util.HashMap;
import java.util.Map;

import es.damas.controllers.InteractorController;
import es.damas.controllers.PlayController;
import es.damas.controllers.ResumeController;
import es.damas.controllers.StartController;
import es.damas.models.Game;
import es.damas.models.State;
import es.damas.models.StateValue;
import es.damas.views.View;

class Draughts {
    
    private View view;
    private Game game;
    private State state;
	private Map<StateValue, InteractorController> controllers;

    private Draughts(){
        this.view = new View();
		this.game = new Game();
		this.state = new State();
        this.controllers = new HashMap<StateValue, InteractorController>();
		this.controllers.put(StateValue.INITIAL, new StartController(this.game, this.state));
		this.controllers.put(StateValue.IN_GAME, new PlayController(this.game, this.state));
		this.controllers.put(StateValue.FINAL, new ResumeController(this.game, this.state));
		this.controllers.put(StateValue.EXIT, null);        
    }

    private void play() {
        InteractorController controller;
		do {
			controller = this.getController();
			if (controller != null)
				this.view.interact(controller);
		} while (controller != null); 
    }
    
    public InteractorController getController() {
		return this.controllers.get(this.state.getValueState());
    }

    public static void main(String[] args){
        new Draughts().play();
    }
    
}