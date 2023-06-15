package cs3500.pa05.model;

public class Task extends Occasion {
  private boolean isCompleted;

  public Task(String n, String des, String d) {
    super(n, des, d);
    isCompleted = false;
  }

  public void updateCompletion() {
    isCompleted = true;
  }

  @Override
  public String toString() {
    String string = "Event: "
        + name
        + System.lineSeparator()
        + makeDescription()
        + "Completed: "
        + completion()
        + System.lineSeparator();
    return string;
  }

  private String completion() {
    if (isCompleted) {
      return "Yes";
    } else {
      return "No";
    }
  }
}
