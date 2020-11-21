package es.damas.views;

import es.damas.controllers.InteractorController;
import es.damas.controllers.InteractorControllersVisitor;
import es.damas.controllers.PlayController;
import es.damas.controllers.ResumeController;
import es.damas.controllers.StartController;
import es.damas.utils.Console;
import es.damas.utils.YesNoDialog;

public class View implements InteractorControllersVisitor {

    private PlayView playView;
    private Console console;
    private YesNoDialog yesNoDialog;
    
    private static final String TITTLE = "Draughts";    
    private static final String MESSAGE = "¿Queréis jugar otra";


    public View(){
    	this.console = new Console();
        this.playView = new PlayView();
        this.yesNoDialog = new YesNoDialog();
    }

    public void interact(InteractorController controller) {
        assert controller != null;
        controller.accept(this);
    }

    @Override
    public void visit(StartController startController) {
    	assert startController != null;
        this.console.writeln(TITTLE);
        new GameView().write(startController);
        startController.start();
    }

    @Override
    public void visit(PlayController playController) {
        assert playController != null;
        this.playView.interact(playController);
    }

    @Override
    public void visit(ResumeController resumeController) {
    	assert resumeController != null;
        if (this.yesNoDialog.read(MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }
    
}
