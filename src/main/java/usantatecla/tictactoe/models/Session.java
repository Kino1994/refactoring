package usantatecla.tictactoe.models;

import usantatecla.tictactoe.types.Token;

public class Session {

	private Game game;

	public Session() {
		this.game = new Game();
	}

	public Token getToken(Coordinate coordinate) {
		return this.game.getToken(coordinate);
	}

	public void next() {
		
	}

	public void setUsers(int users) {
		this.game.setUsers(users);
	}

	public int getMaxPlayers() {
		return this.game.getMaxPlayers();
	}

	public void reset() {
		this.game.reset();
	}

	public boolean isBoardComplete() {
		return this.game.isBoardComplete();
	}

	public boolean isTicTacToe() {
		return this.game.isTicTacToe();
	}

	public Token getToken() {
		return this.game.getToken();
	}

	public boolean isUser() {
		return this.game.isUser();
	}

}
