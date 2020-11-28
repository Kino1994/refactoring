package usantatecla;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BuilderIntervalTest {

  private double min;
  private double max;
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.min = -2.2;
    this.max = 4.4;
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervalBuilderWhenOpenOpen(){
    Interval interval = intervalBuilder.open(this.min).open(this.max).build();
    assertEquals(interval, new Interval(new Min(this.min), new Max(this.max)));
  }

  @Test
  public void givenIntervalBuilderWhenOpenClosed(){
    Interval interval = intervalBuilder.open(this.min).closed(this.max).build();
    assertEquals(interval, new Interval(new Min(this.min), new ClosedMax(this.max)));
  }

  @Test
  public void givenIntervalBuilderWhenClosedOpen(){
    Interval interval = intervalBuilder.closed(this.min).open(this.max).build();
    assertEquals(interval, new Interval(new ClosedMin(this.min), new Max(this.max)));
  }

  @Test
  public void givenIntervalBuilderWhenClosedClosed(){
    Interval interval = intervalBuilder.closed(this.min).closed(this.max).build();
    assertEquals(interval, new Interval(new ClosedMin(this.min), new ClosedMax(this.max)));
  }
  
  @Test
  public void givenNoPointIntervalThenThrowAssertionError() {
	  assertThrows(AssertionError.class, ()-> {
		  intervalBuilder.open(this.max).open(this.max).build();
	  });
  }
    
  @Test
  public void givenIntervalofAnyTypeWhenBuildAndMinGreaterThanMaxThenAssertionError() {
	  assertThrows(AssertionError.class, ()-> {
		  intervalBuilder.open(this.max).open(this.min).build();
	  });
	  assertThrows(AssertionError.class, ()-> {
		  intervalBuilder.open(this.max).closed(this.min).build();
	  });
	  assertThrows(AssertionError.class, ()-> {
		  intervalBuilder.closed(this.max).open(this.min).build();
	  });
	  assertThrows(AssertionError.class, ()-> {
		  intervalBuilder.closed(this.max).closed(this.min).build();
	  });
  }

}
