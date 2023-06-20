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
  BLUE("\"BLUE\"", new Font("Arial Bold", 25), new Font("Arial", 18),
      new Font("Arial Bold Italic", 48), Color.DARKSLATEBLUE, "3.png", "#6495ED"),

  /**
   * Light theme
   */
  LIGHT("\"LIGHT\"", new Font("Georgia Bold", 25), new Font("Georgia", 18),
      new Font("Georgia Bold Italic", 48), Color.DEEPPINK, "1.png", "#fff8ff"),

  /**
   * Dark theme
   */
  DARK("\"DARK\"", new Font("Times New Roman Bold", 25), new Font("Times New Roman", 18),
      new Font("Times New Roman Bold Italic", 48), Color.BLACK,"2.png", "#A9A9A9");


  private String stringRep;
  private Font headerFont;
  private Font stuffFont;
  private Font otherFont;
  private Color fontColor;
  private String image;
  private String backgroundColor;

  Theme(String str, Font header, Font stuff, Font other,
        Color color, String image, String background) {
    this.stringRep = str;
    this.headerFont = header;
    this.stuffFont = stuff;
    this.otherFont = other;
    this.fontColor = color;
    this.image = image;
    this.backgroundColor = background;
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
