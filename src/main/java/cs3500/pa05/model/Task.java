package cs3500.pa05.model;

public class Task extends Occasion {
  private boolean isCompleted;

  public Task(String n, String des, Day d) {
    super(n, des, d);
    isCompleted = false;
  }

  public void updateCompletion() {
    isCompleted = true;
  }
}
