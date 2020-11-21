package es.damas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import es.damas.controllers.AllControllersTest;
import es.damas.models.AllModelsTest;
import es.damas.utils.AllUtilsTest;
import es.damas.views.AllViewsTest;



@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    AllControllersTest.class, 
    AllModelsTest.class,
    AllUtilsTest.class,
    AllViewsTest.class})
public class AllTest {
    
}