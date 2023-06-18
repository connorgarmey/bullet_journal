package cs3500.pa05.model;

/**
 * The 3 possible 3 available to a user
 */
public enum Theme {

  /**
   * Blue theme
   */
  BLUE("\"BLUE\""),

  /**
   * Light theme
   */
  LIGHT("\"LIGHT\""),

  /**
   * Dark theme
   */
  DARK("\"LIGHT\"");


  private String stringRep;

  Theme(String str) {
    this.stringRep = str;
  }

  @Override
  public String toString() {
    return stringRep;
  }
}
