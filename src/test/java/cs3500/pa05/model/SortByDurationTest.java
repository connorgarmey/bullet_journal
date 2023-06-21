package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortByDurationTest {
  EventTest eventTest = new EventTest();
  TaskTest taskTest = new TaskTest();
  SortByDuration duration = new SortByDuration();
  
  @BeforeEach
  public void init() {
    eventTest.setup();
    taskTest.setup();
  }

  @Test
  public void testCompare() {
    int difference = duration.compare(eventTest.event3, eventTest.event4);
    int difference2 = duration.compare(eventTest.event1, eventTest.event2);
    int difference3 = duration.compare(eventTest.event1, eventTest.event1);
    assertEquals(difference, 20);
    assertEquals(difference2, -30);
    assertEquals(difference3, 0);
  }

  @Test
  public void testIllegalArgument() {
    assertThrows(IllegalArgumentException.class,
        () -> duration.compare(taskTest.task1, taskTest.task2));
  }
}