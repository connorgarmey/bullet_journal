package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TaskTest {
  static Task task1;
  static Task task2;
  static TaskJson taskJson1;

  @BeforeAll
  public static void setup() {
    task1 = new Task("PA01", "Need to finish testing PA01", "Monday");
    task2 = new Task("PA02", "", "Friday");
    taskJson1 = new TaskJson("PA01", "Need to finish testing PA01", "Monday", false);
  }

  @AfterEach
  public void restoreInitialValues() {
    task1 = new Task("PA01", "Need to finish testing PA01", "Monday");
    taskJson1 = new TaskJson("PA01", "Need to finish testing PA01", "Monday", false);
  }

  @Test
  public void testJsonToTask() {
    // Utilize the constructor to create a Task from a TaskJson
    Task jsonTask = new Task(taskJson1);

    String outputString = jsonTask.toString();
    String expected = "What: "
        + "PA01"
        + System.lineSeparator()
        + "Description: "
        + "Need to finish testing PA01"
        + System.lineSeparator()
        + "Completed: "
        + "No"
        + System.lineSeparator();
    assertEquals(expected, outputString);
  }

  @Test
  public void testMakeJson() {
    TaskJson taskJson = task1.makeJson();
    assertEquals(taskJson1, taskJson);
  }

  @Test
  public void testToString() {
    // Create the expected output of utilizing toString() on the created task1
    String expected = "What: "
        + "PA01"
        + System.lineSeparator()
        + "Description: "
        + "Need to finish testing PA01"
        + System.lineSeparator()
        + "Completed: "
        + "No"
        + System.lineSeparator();

    // Check that it outputs the expected string
    assertEquals(expected, task1.toString());
  }

  @Test
  public void testGetCompletionStatus() {
    // Check that it is not completed
    boolean isCompleted = task1.getCompletionStatus();
    assertFalse(isCompleted);

    // Set it to completed
    task1.updateCompletion();
    boolean isCompleted2 = task1.getCompletionStatus();
    assertTrue(isCompleted2);

    // Check that completion now says "Yes"
    String expected = "What: "
        + "PA01"
        + System.lineSeparator()
        + "Description: "
        + "Need to finish testing PA01"
        + System.lineSeparator()
        + "Completed: "
        + "Yes"
        + System.lineSeparator();

    // Check that it outputs the expected string
    assertEquals(expected, task1.toString());
  }

  @Test
  public void testGetName() {
    String name = task1.getName();
    assertEquals("PA01", name);
  }

  @Test
  public void testMakeDescription() {
    String taskDescription = task2.makeDescription();
    assertEquals("", taskDescription);
  }
}


