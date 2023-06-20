package cs3500.pa05.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * The 3 possible 3 available to a user
 */
public enum Theme {

  /**
   * Blue theme
   */
  BLUE( new CustomTheme("Arial", "Blue", "BLUE", "Blue", "BLUE")),

  /**
   * Light theme
   */
  LIGHT( new CustomTheme("Georgia", "Pink", "Light Pink", "Light", "LIGHT")),

  /**
   * Dark theme
   */
  DARK( new CustomTheme("Times New Roman", "Black", "Dark Grey", "Dark", "DARK"));

  private CustomTheme theTheme;
  Theme(CustomTheme theme) {
    this.theTheme = theme;
  }

  public CustomTheme getTheTheme() {
    return theTheme;
  }
}
