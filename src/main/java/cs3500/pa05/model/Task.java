package cs3500.pa05.model;

import cs3500.pa05.model.json.TaskJson;

/**
 * Representing an individual Task
 */
public class Task extends Occasion {
  private boolean isCompleted;

  /**
   * Instantiates a new Task, given the task information inputs
   *
   * @param n name of the task
   * @param des optional description
   * @param d day the task will be done
   */
  public Task(String n, String des, String d) {
    super(n, des, d);
    isCompleted = false;
  }

  /**
   * Instantiates a new Task, given a TaskJson
   *
   * @param taskJson a JSON representation of a Task
   */
  public Task(TaskJson taskJson) {
    super(taskJson.name(), taskJson.description(), taskJson.dayOfWeek());
    isCompleted = false;
  }

  /**
   * Constructs a TaskJson from this Task
   *
   * @return TaskJson representation of the Task
   */
  public TaskJson makeJson() {
    return new TaskJson(this.name, this.description, this.day, this.isCompleted);
  }

  /**
   * Updates the completion status to true
   */
  public void updateCompletion() {
    isCompleted = true;
  }

  /**
   * Overrides the toString method to produce a Task written as a String
   *
   * @return String representation of the Task specifics
   */
  @Override
  public String toString() {
    return "What: "
        + name
        + System.lineSeparator()
        + makeDescription()
        + "Completed: "
        + completion()
        + System.lineSeparator();
  }

  /**
   * Returns the completion status as a string
   *
   * @return a String "Yes" or "No"
   */
  private String completion() {
    if (isCompleted) {
      return "Yes";
    } else {
      return "No";
    }
  }

  /**
   * Returns the completion status as a boolen
   *
   * @return true or false
   */
  public boolean getCompletion() {
    return this.isCompleted;
  }

  /**
   * Gets the completion status
   *
   * @return whether a task is complete
   */
  public boolean getCompletionStatus() {
    return isCompleted;
  }

}
