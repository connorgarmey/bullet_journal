package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DayTest {
  Day monday;
  Day monday2;
  Day tuesday;

  @BeforeAll
  public void setup() {
    monday = new Day("Monday");
    monday2 = new Day("Monday");
    tuesday = new Day("Tuesday");
  }

  @Test
  private void testAddEvent() {
    // Assert that initial day has no events
    int countEvents1 = monday.getNumEvents();
    assertEquals(0, countEvents1);

    // Add an event and check that the number of events increased
    monday.addEvent(EventTest.event1);
    int countEvents2 = monday.getNumEvents();
    assertEquals(1, countEvents2);
  }

  @Test
  private void testAddTask() {
    // Assert that the initial day has no tasks
    int countTasks1 = monday.getNumTasks();
    assertEquals(0, countTasks1);

    // Add a task and check that the count of tasks increased
    monday.addTask(TaskTest.task1);
    int countTasks2 = monday.getNumTasks();
    assertEquals(1, countTasks2);
  }

  @Test
  private void testEquals() {
    // Check two days that are not equal
    assertFalse(monday.equals(tuesday));

    // Check two days that are equal
    assertTrue(monday.equals(monday2));
  }

  @Test
  private void testIsSameDay() {
    // Check two days that are not equal
    assertFalse(monday.isSameDay("Tuesday"));

    // Check two days that are equal
    assertTrue(monday.isSameDay("Monday"));
  }



}