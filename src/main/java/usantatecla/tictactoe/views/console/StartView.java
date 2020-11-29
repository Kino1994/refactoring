package usantatecla.tictactoe.views.console;

import usantatecla.tictactoe.controllers.StartController;
import usantatecla.tictactoe.views.Message;
import usantatecla.utils.Console;
import usantatecla.utils.LimitedIntDialog;

class StartView {

	private StartController startController;

	StartView(StartController startController) {
		assert startController != null;
		
		this.startController = startController;
	}

	void interact(StartController startController) {
		Message.TITTLE.getMessage();
		int users = new LimitedIntDialog(0, 
			startController.getMaxPlayers()).read(Message.NUMBER_PLAYERS.toString());
		startController.setUsers(users);
		new GameView(startController).write();
	}
	}
