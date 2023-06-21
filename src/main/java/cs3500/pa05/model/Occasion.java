package cs3500.pa05.model;

/**
 * represents the abstraction of an event or task
 */
public abstract class Occasion {
  protected String name;
  protected String description;
  protected String day;

  /**
   * constructs with a name, description and day
   *
   * @param n the name of the occasion
   * @param des a description of the occasion
   * @param d string rep of the occasion
   */
  public Occasion(String n, String des, String d) {
    this.name = n;
    this.description = des;
    this.day = d;
  }

  /**
   * getter of the name of the occasion
   *
   * @return this name
   */
  public String getName() {
    return this.name;
  }

  protected String makeDescription() {
    if (description.equals("")) {
      return "";
    } else {
      return "Description: "
          + description
          + System.lineSeparator();
    }
  }

  /**
   * Returns whether or not two occasions are the same
   *
   * @param otherName the name of the other occasion being compared
   * @return boolean true or false
   */
  public boolean sameOccasion(String otherName) {
    return this.name.equalsIgnoreCase(otherName);
  }

}
