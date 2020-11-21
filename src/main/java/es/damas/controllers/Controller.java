package es.damas.controllers;

import java.util.HashMap;
import java.util.Map;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.Piece;
import es.damas.models.State;
import es.damas.models.StateValue;
import es.damas.views.View;

public abstract class Controller {

    Game game;
    private View view;
    
    static State state;
	private static Map<StateValue, Controller> controllers = new HashMap<StateValue, Controller>();
    
	static {
		state = new State();
		controllers = new HashMap<StateValue, Controller>();
		controllers.put(StateValue.INITIAL, new StartController());
		controllers.put(StateValue.IN_GAME, new PlayController());
		controllers.put(StateValue.FINAL, new ResumeController());
		controllers.put(StateValue.EXIT, null);
	}

    public Controller() {
        this.view = new View();
		this.game = new Game();
    }

    public Color getColor(Coordinate coordinate) {
        assert coordinate != null;
        return this.game.getColor(coordinate);
    }

    public int getDimension() {
        return this.game.getDimension();
    }
    
    public Piece getPiece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}
    
    public static Controller getInstance() {
  		return controllers.get(state.getValueState());
    }
    
    public View getView() {
    	return this.view;
    }
    
    public abstract void control(View view);
    

}
