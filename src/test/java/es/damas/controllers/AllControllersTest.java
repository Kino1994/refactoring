package es.damas.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    ControllerTest.class,
    PlayControllerTest.class,
    ResumeControllerTest.class,
    StartControllerTest.class})
public class AllControllersTest {

}
