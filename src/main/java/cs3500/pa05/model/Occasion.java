package cs3500.pa05.model;

public class Occasion {
  protected String name;
  protected String description;
  protected Day day;

  public Occasion(String n, String des, Day d) {
    this.name = n;
    this.description = des;
    this.day = d;
  }
}
