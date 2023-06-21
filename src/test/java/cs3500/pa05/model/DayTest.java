package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    wednesday = new Day("Wednesday", listOfEvents, listOfTasks);
  }

  @Test
  public void testDayConstructor() {
    // Use the constructor to construct a new day
    Day newDay = new Day("Wednesday", listOfEvents, listOfTasks);

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
  public void testSortListEvents() {

  }

  @Test
  public void testSortListTasks() {

  }

  @Test
  public void testToString() {
    //
    String output = wednesday.toString();

    //
    String expected = "Events: \n" +
        "What: PA01\n" +
        "Description: Need to finish testing PA01\n" +
        "Completed: No\n" +
        "\n" +
        "Tasks: \n" +
        "What: Haircut\n" +
        "Description: Pre-scheduled haircut\n" +
        "Start Time: 10:30\n" +
        "Duration: 30 minutes\n" +
        "\n";

    // Assert
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
    // for getNumCompletedTasks to work, the list of tasks must be of type Task (not occasion)
    // but for toString to work on the day, it requires it must be type Occasion (not task / event)
      // might be because stringOccasions requests a type occasion (can we make two separate ones for each type?)


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



}