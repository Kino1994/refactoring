package es.damas;

import java.util.HashMap;
import java.util.Map;

import es.damas.controllers.Controller;
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
	private Map<StateValue, Controller> controllers;

    private Draughts(){
        this.view = new View();
		this.game = new Game();
		this.state = new State();
        this.controllers = new HashMap<StateValue, Controller>();
		this.controllers.put(StateValue.INITIAL, new StartController(this.game, this.state));
		this.controllers.put(StateValue.IN_GAME, new PlayController(this.game, this.state));
		this.controllers.put(StateValue.FINAL, new ResumeController(this.game, this.state));
		this.controllers.put(StateValue.EXIT, null);        
    }

    private void play() {
        Controller controller;
		do {
			controller = this.getController();
			if (controller != null)
				controller.control(this.view);
		} while (controller != null); 
    }
    
    public Controller getController() {
		return this.controllers.get(this.state.getValueState());
    }

    public static void main(String[] args){
        new Draughts().play();
    }
    
}