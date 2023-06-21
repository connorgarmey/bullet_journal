package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
  public Occasion task1;
  public Occasion task2;
  public TaskJson taskJson1;

  @BeforeEach
  public void setup() {
    task1 = new Task("PA01", "Need to finish testing PA01", "Monday");
    task2 = new Task("PA02", "", "Friday");
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
    TaskJson taskJson = null;
    if (task1 instanceof Task t) {
      taskJson = t.makeJson();
    }
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
    boolean isCompleted = true;
    boolean isCompleted2 = false;
    if (task1 instanceof Task t) {
      isCompleted = t.getCompletionStatus();
      t.updateCompletion();
      isCompleted2 = t.getCompletionStatus();
    }
    assertFalse(isCompleted);
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


