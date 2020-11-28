package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntervalTest {
  
  private Point left = new Point(-2.2);
  private Point right = new Point(4.4);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.right = new Point(4.4);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }
  
  @Test
  public void givenNullIntervalwhenisIntersectedThrowAssertionError() {
	  Interval interval = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  assertThrows(AssertionError.class, ()-> {
		  interval.isIntersected(null);
	  });
  }
    
  @Test
  public void givensameIntervalswhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));	
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givensameIntervalButSecondisClosedwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givensameIntervalButFistisClosedwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givensameIntervalBothAreClosedwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));	 
	  assertTrue(toCheck.isIntersected(interval));

  }
  
  @Test
  public void givensameIntervalsAreBothOpenClosedwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));	
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givensameIntervalsAreBothClosedOpenwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givensameIntervalButFirstisOpenClosedAndSecondisClosedOpenOpenwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givensameIntervalButFirstisClosedOpenAndSecondisOpenClosedwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));	
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenIntersecteIntervalwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(-3.0).open(left.getGreater()).build();
	  Interval toCheck = intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenNotIntersecteIntervalwhenisIntersectedThenFalse() {
	  Interval interval = intervalBuilder.open(-3.0).open(left.getEquals()).build();
	  Interval toCheck = intervalBuilder.open(left.getGreater()).open(right.getEquals()).build();
	  assertFalse(interval.isIntersected(toCheck));	 
	  assertFalse(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenNotIntersecteIntervalInthePointwhenisIntersectedThenFalse() {
	  Interval interval = intervalBuilder.open(-3.0).open(left.getEquals()).build();
	  Interval toCheck = intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
	  assertFalse(interval.isIntersected(toCheck));	 
	  assertFalse(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenIntersecteIntervalInThePointwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(-3.0).open(left.getEquals()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenNotIntersecteIntervalInThePointCase2whenisIntersectedThenFalse() {
	  Interval interval = intervalBuilder.open(right.getEquals()).open(right.getGreater()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
	  assertFalse(interval.isIntersected(toCheck));
	  assertFalse(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenIntersecteIntervalInThePointCase2whenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.open(right.getEquals()).open(right.getGreater()).build();
	  Interval toCheck = intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
  @Test
  public void givenPointIntervalwhenisIntersectedThenTrue() {
	  Interval interval = intervalBuilder.closed(right.getEquals()).closed(right.getEquals()).build();
	  Interval toCheck = intervalBuilder.closed(right.getEquals()).closed(right.getEquals()).build();
	  assertTrue(interval.isIntersected(toCheck));
	  assertTrue(toCheck.isIntersected(interval));
  }
  
}