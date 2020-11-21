package es.damas.views;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import es.damas.models.Color;
import es.damas.models.Coordinate;
import es.damas.models.Game;
import es.damas.models.Piece;
import es.damas.utils.Console;
import es.damas.utils.YesNoDialog;

public class View {

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
    
    public View(){
    	this.console = new Console();
        this.yesNoDialog = new YesNoDialog();
    }

    public boolean continuePlaying() {
        return this.yesNoDialog.read(MESSAGE);
    }
        
    public Coordinate[] getCoordinates(String input) {
        assert this.isMoveFormat(input);
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        while (input.length() > 0){
            coordinateList.add(Coordinate.getInstance(input.substring(0, 2)));
            input = input.substring(2, input.length());
            if (input.length() > 0 && input.charAt(0) == '.')
                input = input.substring(1, input.length());
        }
        Coordinate[] coordinates = new Coordinate[coordinateList.size()];
        for(int i=0; i< coordinates.length; i++){
            coordinates[i] = coordinateList.get(i);
        }
        return coordinates;
    }
    
    public void writeMenu(int dimension, Game game) {
    	this.writeTittle();
        this.writeNumbersLine(dimension);
        for (int i = 0; i < dimension; i++)
            this.writePiecesRow(i, dimension, game);
        this.writeNumbersLine(dimension);
    }

    private void writeNumbersLine(int dimension) {
        this.console.write(" ");
        for (int i = 0; i < dimension; i++)
            this.console.write((i + 1) + "");
        this.console.writeln();
    }

    private void writePiecesRow(final int row, int dimension, Game game) {
        this.console.write((row + 1) + "");
        for (int j = 0; j < dimension; j++) {
            Piece piece = game.getPiece(new Coordinate(row, j));
            if (piece == null)
                this.console.write(" ");
            else 
                this.console.write(piece.getCode());
        }
        this.console.writeln((row + 1) + "");
    }

    public String read(Color color) {
        final String titleColor = PROMPT.replace(COLOR_PARAM,COLOR_VALUES[color.ordinal()]);
        return this.console.readString(titleColor);
    }

    public boolean isCanceledFormat(String input) {
        return input.equals(CANCEL_FORMAT);
    }

    public boolean isMoveFormat(String input) {
        return Pattern.compile(MOVEMENT_FORMAT).matcher(input).find();
    }

    public void writeError(){
        this.console.writeln(ERROR_MESSAGE);
    }

    public void writeLost() {
        this.console.writeln(LOST_MESSAGE);
    }
    
    public void writeTittle () {
    	this.console.writeln(TITTLE);
    }
    
}
