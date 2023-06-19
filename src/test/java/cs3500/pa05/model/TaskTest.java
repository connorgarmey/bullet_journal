package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TaskTest {
  static Task task1;
  static TaskJson taskJson1;

  @BeforeAll
  public void setup() {
    task1 = new Task("PA01", "Need to finish testing PA01", "Monday");
    taskJson1 = new TaskJson("PA01", "Need to finish testing PA01", "Monday", false);
  }

  @Test
  private void testJsonToTask() {
    // Utilize the constructor to create a Task from a TaskJson
    Task expected = new Task(taskJson1);

    // Check that the TaskJson fields align with the Task field created with the constructor
    assertEquals("PA01", expected.getName());
    assertEquals("Need to finish testing PA01", expected.getDescription());
    assertEquals("Monday", expected.getDay());
    assertFalse(expected.getCompleted());
  }

  @Test
  private void testToString() {
    // Create the expected output of utilizing toString() on the created task1
    String expected = "What: "
        + "PA01"
        + System.lineSeparator()
        + "Need to finish testing PA01"
        + "Completed: "
        + "No"
        + System.lineSeparator();

    // Check that it outputs the expected string
    assertEquals(expected, task1.toString());
  }

}

