package es.damas;

import es.damas.controllers.Controller;

class Draughts {
    
    private Draughts(){
       
    }

    private void play() {
    	Controller controller;
        do {
        	controller = Controller.getInstance();
			if (controller != null)
				controller.control(controller.getView());
		} while (controller != null); 
    }
    
    public static void main(String[] args){
        new Draughts().play();
    }
    
}