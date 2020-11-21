package es.damas.views;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.print.event.PrintJobAttributeEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.damas.controllers.ResumeController;
import es.damas.utils.YesNoDialog;

@RunWith(MockitoJUnitRunner.class)
public class ResumeViewTest {
	
	@Mock
	private ResumeController resumeController;
	
	@Mock
	private YesNoDialog yesNoDialog;
	
	@InjectMocks
    private ResumeView resumeView = new ResumeView();	
	
	@Test
    public void testGivenPlayControllerWhenInteractThenIsCorrect() {
		doNothing().when(resumeController).next();
		when(yesNoDialog.read(anyString())).thenReturn(true);
		resumeView.interact(resumeController);
		verify(resumeController).reset();
	}
	
	@Test
    public void testGivenPlayControllerWhenInteractThenIsNotCorrect() {
		doNothing().when(resumeController).reset();
		when(yesNoDialog.read(anyString())).thenReturn(false);
		resumeView.interact(resumeController);
		verify(resumeController).next();
	}

}
