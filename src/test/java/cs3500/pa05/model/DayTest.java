package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DayTest {
  static Day monday;
  static Day monday2;
  static Day tuesday;
  static ArrayList<Occasion> listOfTasks;
  static ArrayList<Occasion> listOfEvents;

  @BeforeAll
  public static void setup() {
    monday = new Day("Monday");
    monday2 = new Day("Monday");
    tuesday = new Day("Tuesday");

    listOfEvents = new ArrayList<>();
    listOfTasks = new ArrayList<>();

    listOfEvents.add(EventTest.event1);
    listOfEvents.add(EventTest.event2);

    listOfTasks.add(TaskTest.task1);
    listOfTasks.add(TaskTest.task2);
  }

  @Test
  public void testDayConstructor() {
    // Use the constructor to construct a new day
    Day newDay = new Day("Wednesday", listOfEvents, listOfTasks);

    // Check
    int numEvents = newDay.getNumEvents();
    int numTasks = newDay.getNumTasks();

    assertEquals(2, numEvents);
    assertEquals(2, numTasks);
  }

  @Test
  public void testAddEvent() {
    // Assert that initial day has no events
    int countEvents1 = monday.getNumEvents();
    assertEquals(0, countEvents1);

    // Add an event and check that the number of events increased
    monday.addEvent(EventTest.event1);
    int countEvents2 = monday.getNumEvents();
    assertEquals(1, countEvents2);
  }

  @Test
  public void testAddTask() {
    // Assert that the initial day has no tasks
    int countTasks1 = monday.getNumTasks();
    assertEquals(0, countTasks1);

    // Add a task and check that the count of tasks increased
    monday.addTask(TaskTest.task1);
    int countTasks2 = monday.getNumTasks();
    assertEquals(1, countTasks2);
  }

  @Test
  public void testEquals() {
    // Check two days that are not equal
    assertFalse(monday.equals(tuesday));

    // Check two days that are equal
    assertTrue(monday.equals(monday2));

    // Check that if the object is not a day, it returns false
    String string = "";
    assertFalse(monday.equals(string));
  }

  @Test
  public void testIsSameDay() {
    // Check two days that are not equal
    assertFalse(monday.isSameDay("Tuesday"));

    // Check two days that are equal
    assertTrue(monday.isSameDay("Monday"));
  }

  @Test
  public void testSortListEvents() {

  }

  @Test
  public void testSortListTasks() {

  }

  @Test
  public void testToString() {

  }



}