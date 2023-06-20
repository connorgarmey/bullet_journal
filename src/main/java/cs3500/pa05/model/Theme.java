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

  public Font getHeaderFont() {
    return headerFont;
  }

  public Font getStuffFont() {
    return stuffFont;
  }

  public Font getOtherFont() {
    return otherFont;
  }

  public Color getFontColor() {
    return fontColor;
  }

  public String getImage() {
    return image;
  }

  public String getBackgroundColor() {
    return backgroundColor;
  }
}
