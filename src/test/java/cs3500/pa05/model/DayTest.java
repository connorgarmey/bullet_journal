package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.json.DayJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing for the Day class
 */
class DayTest {
  Day monday;
  Day monday2;
  Day tuesday;
  Day wednesday;
  ArrayList<Occasion> listOfTasks;
  ArrayList<Occasion> listOfEvents;
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

    assertEquals(0, wednesday.getNumCompletedTasks());
    wednesday.addTask(eventTest.event1);
    Day d = new Day("Monday", listOfEvents, listOfTasks);
    d.makeDayJson();
    d.taskComplete("Haircut");
    assertEquals(0, wednesday.getNumCompletedTasks());
    wednesday.addTask(taskTest.task1);
    d.updateCompletion("Haircut");
    d.updateCompletion("PA01");
    assertEquals(0, wednesday.getNumCompletedTasks());
    d.updateCompletion("Haircut");
    Task t = (Task) taskTest.task1;
    t.updateCompletion();
    assertEquals(2, wednesday.getNumCompletedTasks());
  }

  @Test
  public void testMakeDayJson() {
    DayJson wednesdayJson = wednesday.makeDayJson();

    assertEquals("Wednesday", wednesdayJson.day());

    assertEquals("{\n" +
        "  \"day\": \"Wednesday\",\n" +
        "  \"events\": [\n" +
        "    {\n" +
        "  \"name\": \"Haircut\",\n" +
        "  \"description\": \"Pre-scheduled haircut\",\n" +
        "  \"day-of-week\": \"Monday\",\n" +
        "  \"start-hour\": 10,\n" +
        "  \"start-minute\": 30,\n" +
        "  \"duration\": 30\n" +
        "}\n" +
        "  ],\n" +
        "  \"tasks\": [\n" +
        "    {\n" +
        "  \"name\": \"PA01\",\n" +
        "  \"description\": \"Need to finish testing PA01\",\n" +
        "  \"day-of-week\": \"Monday\",\n" +
        "  \"completed\": false\n" +
        "}\n" +
        "  ]\n" +
        "}", wednesday.makeDayJson().toString());

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
    listOfTasks.add(taskTest.task2);
    assertThrows(
        IllegalArgumentException.class,
        () -> listOfTasks.sort(new SortByDuration()));
  }





}