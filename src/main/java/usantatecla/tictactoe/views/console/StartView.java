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
	
	}
	
	void interact() {
		Console.getInstance().writeln(Message.TITTLE.toString());
		int users = new LimitedIntDialog(0, 
			this.startController.getMaxPlayers()).read(Message.NUMBER_PLAYERS.toString());
		this.startController.setUsers(users);
		new GameView(startController).write();
	}

}
