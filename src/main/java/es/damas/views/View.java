package es.damas.views;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import es.damas.controllers.InteractorController;
import es.damas.controllers.InteractorControllersVisitor;
import es.damas.controllers.PlayController;
import es.damas.controllers.ResumeController;
import es.damas.controllers.StartController;
import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Error;
import es.damas.utils.Console;
import es.damas.utils.YesNoDialog;

public class View implements InteractorControllersVisitor {

    private Console console;
    private YesNoDialog yesNoDialog;
    
    private static final String COLOR_PARAM = "#color";
    private static final String[] COLOR_VALUES = { "blancas", "negras" };
    private static final String PROMPT = "Mueven las " + COLOR_PARAM + ": ";
    private static final String CANCEL_FORMAT = "-1";
    private static final String MOVEMENT_FORMAT = "[1-8]{2}(\\.[1-8]{2}){1,2}";
    private static final String ERROR_MESSAGE = "Error!!! Formato incorrecto";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";
    private static final String TITTLE = "Draughts";    
    private static final String MESSAGE = "¿Queréis jugar otra";
    
    private String string;


    public View(){
    	this.console = new Console();
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
        Error error;
        do {
            error = null;
            this.string = this.read(playController.getColor());
            if (this.isCanceledFormat())
                playController.cancel();
            else if (!this.isMoveFormat()) {
                error = Error.BAD_FORMAT;
                this.writeError();
            } else {
                error = playController.move(this.getCoordinates());
                new GameView().write(playController);
                if (error == null && playController.isBlocked())
                    this.writeLost();
            }
        } while (error != null);
    }

    @Override
    public void visit(ResumeController resumeController) {
    	assert resumeController != null;
        if (this.yesNoDialog.read(MESSAGE))
            resumeController.reset();
        else
            resumeController.next();
    }
        
    private Coordinate[] getCoordinates() {
        assert this.isMoveFormat();
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        while (string.length() > 0){
            coordinateList.add(Coordinate.getInstance(string.substring(0, 2)));
            string = string.substring(2, string.length());
            if (string.length() > 0 && string.charAt(0) == '.')
                string = string.substring(1, string.length());
        }
        Coordinate[] coordinates = new Coordinate[coordinateList.size()];
        for(int i=0; i< coordinates.length; i++){
            coordinates[i] = coordinateList.get(i);
        }
        return coordinates;
    }

    private String read(Color color) {
        final String titleColor = PROMPT.replace(COLOR_PARAM,COLOR_VALUES[color.ordinal()]);
        return this.console.readString(titleColor);
    }

    private boolean isCanceledFormat() {
        return string.equals(CANCEL_FORMAT);
    }

    private boolean isMoveFormat() {
        return Pattern.compile(MOVEMENT_FORMAT).matcher(string).find();
    }

    private void writeError(){
        this.console.writeln(ERROR_MESSAGE);
    }

    private void writeLost() {
        this.console.writeln(LOST_MESSAGE);
    }
    
}
