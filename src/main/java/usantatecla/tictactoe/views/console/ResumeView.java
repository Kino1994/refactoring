package usantatecla.tictactoe.views.console;

import usantatecla.tictactoe.controllers.ResumeController;
import usantatecla.tictactoe.views.Message;
import usantatecla.utils.YesNoDialog;

class ResumeView {

	private ResumeController resumeController;

	public ResumeView(ResumeController resumeController){
		this.resumeController = resumeController;
	}
	
	boolean interact(ResumeController resumeController) {
		boolean isResumed = new YesNoDialog().read(Message.RESUME.toString());
		resumeController.resume(isResumed);
		return isResumed;
	}
}
