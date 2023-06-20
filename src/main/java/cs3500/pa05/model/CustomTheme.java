package cs3500.pa05.model;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CustomTheme {
  private String name;
  private Font stuffFont;
  private Font headerFont;
  private Font otherFont;
  private Color fontColor;
  private String image;
  private String backgroundColor;

  public CustomTheme(String name, Font header, Font stuff, Font other,
                     Color color, String image, String background) {
    this.name = name;
    this.headerFont = header;
    this.stuffFont = stuff;
    this.otherFont = other;
    this.fontColor = color;
    this.image = image;
    this.backgroundColor = background;
  }
}
