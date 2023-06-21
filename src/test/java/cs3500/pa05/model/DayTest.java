package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing for the Day class
 */
class DayTest {
  public Day monday;
  public Day monday2;
  public Day tuesday;
  public Day wednesday;
  public ArrayList<Occasion> listOfTasks;
  public ArrayList<Occasion> listOfEvents;
  public TaskJson taskJson1;
  EventTest eventTest = new EventTest();
  TaskTest taskTest = new TaskTest();

  @BeforeEach
  public void setup() {
    eventTest.setup();
    taskTest.setup();
    monday = new Day("Monday");
    monday2 = new Day("Monday");
    tuesday = new Day("Tuesday");
    listOfEvents = new ArrayList<>();
    listOfTasks = new ArrayList<>();

    listOfEvents.add(eventTest.event1);
    listOfTasks.add(taskTest.task1);

    wednesday = new Day("Wednesday", listOfTasks, listOfEvents);
  }

  @Test
  public void testDayConstructor() {
    // Use the constructor to construct a new day
    Day newDay = new Day("Wednesday", listOfTasks, listOfEvents);

    // Check
    int numEvents = newDay.getNumEvents();
    int numTasks = newDay.getNumTasks();

    assertEquals(1, numEvents);
    assertEquals(1, numTasks);
  }

  @Test
  public void testAddEvent() {
    // Assert that initial day has no events
    int countEvents1 = monday.getNumEvents();
    assertEquals(0, countEvents1);

    // Add an event and check that the number of events increased
    monday.addEvent(eventTest.event1);
    int countEvents2 = monday.getNumEvents();
    assertEquals(1, countEvents2);
  }

  @Test
  public void testAddTask() {
    // Assert that the initial day has no tasks
    int countTasks1 = monday.getNumTasks();
    assertEquals(0, countTasks1);

    // Add a task and check that the count of tasks increased
    monday.addTask(taskTest.task1);
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
    assertFalse(monday.equals(1));
  }

  @Test
  public void testIsSameDay() {
    // Check two days that are not equal
    assertFalse(monday.isSameDay("Tuesday"));

    // Check two days that are equal
    assertTrue(monday.isSameDay("Monday"));
  }

  @Test
  public void testToString() {
    // Store the actual output of toString
    String output = wednesday.toString();

    // Store the expected output of toString
    String expected = "Events: \n"
        + "What: Haircut\n"
        + "Description: Pre-scheduled haircut\n"
        + "Start Time: 10:30\n"
        + "Duration: 30 minutes\n"
        + "\n"
        + "Tasks: \n"
        + "What: PA01\n"
        + "Description: Need to finish testing PA01\n"
        + "Completed: No\n"
        + "\n";

    // Assert they are equal
    assertEquals(expected, output);
  }

  @Test
  public void testGetNumCompletedTasks() {
    // Find the actual number of completed tasks
    int actual = wednesday.getNumCompletedTasks();

    // Assert there are no completed tasks at the beginning
    assertEquals(0, actual);

    //
    //task1.updateCompletion();

  }

  @Test
  public void testMakeDayJson() {
    // Create the Day Json with
    DayJson wednesdayJson = wednesday.makeDayJson();

    // Assert each value of the JSON
    assertEquals("Wednesday", wednesdayJson.day());
    // assertEquals(1, wednesdayJson.events().size());
    // assertEquals(1, wednesdayJson.tasks().size());
  }

  @Test
  public void testTaskComplete() {
    assertFalse(wednesday.taskComplete("PA01"));
    assertFalse(wednesday.taskComplete("hi"));
    wednesday.updateCompletion("PA01");
    assertTrue(wednesday.taskComplete("PA01"));
  }

  @Test
  public void testDeleteOccasionFromList() {
    wednesday.deleteOccasionFromList("Haircut", false);
    assertEquals(wednesday.getNumEvents(), 0);
    wednesday.deleteOccasionFromList("PA01", true);
    assertEquals(wednesday.getNumTasks(), 0);
  }

  @Test
  public void testOccasionExists() {
    assertTrue(wednesday.occasionExists("Haircut", false));
    assertFalse(wednesday.occasionExists("Cry", false));
    assertTrue(wednesday.occasionExists("PA01", true));
    assertFalse(wednesday.occasionExists("Sleep", true));
  }



}