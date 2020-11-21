package es.damas.views;

import es.damas.controllers.StartController;
import es.damas.utils.Console;

class StartView {

    private static final String TITTLE = "Draughts";
    
    private Console console;

    StartView(){
    	this.console = new Console();
    }

    void interact(StartController startController) {
        assert startController != null;
        this.console.writeln(StartView.TITTLE);
        new GameView().write(startController);
        startController.start();
    }

}
