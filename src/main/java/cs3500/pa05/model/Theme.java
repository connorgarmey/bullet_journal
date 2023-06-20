package cs3500.pa05.model;

import cs3500.pa05.view.CustomTheme;

/**
 * The 3 possible 3 available to a user
 */
public enum Theme {

  /**
   * Blue theme
   */
  BLUE( new CustomTheme("Arial", "Blue", "Blue", "Blue", "BLUE")),

  /**
   * Light theme
   */
  LIGHT( new CustomTheme("Georgia", "Pink", "Light Pink", "Light", "LIGHT")),

  /**
   * Dark theme
   */
  DARK( new CustomTheme("Times New Roman", "Black", "Dark Grey", "Dark", "DARK"));

  private CustomTheme theTheme;

  /**
   * constructors a new theme enum with the corresponding custom theme rep
   *
   * @param theme custom theme rep
   */
  Theme(CustomTheme theme) {
    this.theTheme = theme;
  }

  /**
   * getter for the custom theme
   *
   * @return custom theme
   */
  public CustomTheme getTheTheme() {
    return theTheme;
  }
}
