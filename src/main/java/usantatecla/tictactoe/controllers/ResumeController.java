package usantatecla.tictactoe.controllers;

import usantatecla.tictactoe.models.Game;
import usantatecla.tictactoe.models.Session;

public class ResumeController extends Controller {
	
	private Session session;

  public ResumeController(Game game) {
    super(game);
  }

  public void resume(boolean isResumed) {
	    if (isResumed){
	      this.session.reset();
	    } else {
	      this.session.next();
	    }
	  }
  
  public void resume() {
    this.game.reset();
  }

}
