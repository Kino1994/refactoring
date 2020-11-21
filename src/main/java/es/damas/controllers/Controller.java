package es.damas.controllers;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.Piece;
import es.damas.models.State;
import es.damas.views.View;

public abstract class Controller {

    protected Game game;
    protected State state;

    public Controller(Game game, State state) {
        assert game != null;
        assert state != null;
        this.game = game;
        this.state = state;
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
    
    public abstract void control(View view);

}
