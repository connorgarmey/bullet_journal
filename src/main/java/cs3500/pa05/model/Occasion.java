package cs3500.pa05.model;

/**
 * represents the abstraction of an event or task
 */
public abstract class Occasion {
  protected String name;
  protected String description;
  protected Day day;

  public Occasion(String n, String des, Day d) {
    this.name = n;
    this.description = des;
    this.day = d;
  }
}
