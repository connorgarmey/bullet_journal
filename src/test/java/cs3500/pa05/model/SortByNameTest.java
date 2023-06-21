package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortByNameTest {
  EventTest eventTest = new EventTest();
  TaskTest taskTest = new TaskTest();
  SortByName name = new SortByName();

  @BeforeEach
  public void init() {
    eventTest.setup();
    taskTest.setup();
  }

  @Test
  public void testCompare() {
    int difference = name.compare(eventTest.event3, eventTest.event4);
    int difference2 = name.compare(eventTest.event1, eventTest.event2);
    int difference3 = name.compare(taskTest.task2, taskTest.task1);
    int difference4 = name.compare(eventTest.event3, eventTest.event3);
    int difference5 = name.compare(taskTest.task1, taskTest.task1);
    assertEquals(difference, -13);
    assertEquals(difference2, -5);
    assertEquals(difference3, 1);
    assertEquals(difference4, 0);
    assertEquals(difference5, 0);
  }

}